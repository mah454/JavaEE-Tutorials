package ir.moke.javaee.validation;

import ir.moke.javaee.model.Animal;
import ir.moke.javaee.model.Dog;

import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.validation.*;
import java.util.Set;

public class AnimalValidator implements ConstraintValidator<AnimalValidation, JsonObject> {

    @Override
    public void initialize(AnimalValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(JsonObject jsonObject, ConstraintValidatorContext context) {
        if (jsonObject.get("type") != null) {
            Animal animal = getAnimal(jsonObject);
            if (animal != null) {
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<Animal>> errors = validator.validate(animal);
                return errors.size() <= 0;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private static Animal getAnimal(JsonObject jsonObject) {
        String type = jsonObject.getString("type");
        Jsonb jsonb = JsonbBuilder.create();
        switch (type) {
            case "dog":
                return jsonb.fromJson(jsonObject.toString(), Dog.class);
            default:
                return null;
        }
    }
}

