package ir.moke.javaee.mongodb;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface MongoClientDefinition {

    String name();

    String description() default "";

    String url() default "localhost";

    int port() default 27017;

    String dbName() default "";

    String username() default "";

    String password() default "";
}
