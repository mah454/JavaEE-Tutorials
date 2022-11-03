package ir.moke.javaee.interceptor;

import jakarta.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD,TYPE,FIELD})
@Retention(RUNTIME)
@InterceptorBinding
public @interface ValidationInterceptor {
}
