package ir.moke.javaee.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = {AnimalValidation.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ValidAnimal {

    String message() default "This is not valid !";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
