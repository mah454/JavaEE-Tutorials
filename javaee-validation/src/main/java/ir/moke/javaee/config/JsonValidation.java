package ir.moke.javaee.config;

import javax.json.JsonObject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JsonValidation implements ConstraintValidator<ValidAnimal, JsonObject> {

    @Override
    public void initialize(ValidAnimal validAnimalAnnotation) {

    }

    @Override
    public boolean isValid(JsonObject value, ConstraintValidatorContext context) {
        return false;
    }
}
