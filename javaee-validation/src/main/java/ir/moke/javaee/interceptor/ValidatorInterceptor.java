package ir.moke.javaee.interceptor;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Set;

@Interceptor
@ValidationInterceptor
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class ValidatorInterceptor {

    @AroundInvoke
    public Object processValidationException(InvocationContext context) {
        try {
            return context.proceed();
        } catch (Exception e) {
            ConstraintViolationException cve = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> cvList = cve.getConstraintViolations();
            cvList.forEach(error -> System.out.println(error.getMessage()));
            return null;
        }
    }
}
