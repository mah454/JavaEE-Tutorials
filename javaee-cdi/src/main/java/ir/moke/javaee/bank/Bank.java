package ir.moke.javaee.bank;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, TYPE})
@Retention(RUNTIME)
@Qualifier
public @interface Bank {
    BankType value();
}
