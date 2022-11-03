package ir.moke.javaee.mongodb;

import org.bson.Document;

import jakarta.enterprise.util.Nonbinding;
import jakarta.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, TYPE, METHOD, PARAMETER})
@Retention(RUNTIME)
@Qualifier
@Documented
public @interface Mongo {
    @Nonbinding Class<?> type() default Document.class;

    @Nonbinding String collection() default "";
}
