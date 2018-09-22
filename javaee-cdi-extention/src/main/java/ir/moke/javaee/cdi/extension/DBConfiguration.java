package ir.moke.javaee.cdi.extension;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
public @interface DBConfiguration {
    String name();

    String description() default "";

    String hostname();

    String username();

    String password();
}
