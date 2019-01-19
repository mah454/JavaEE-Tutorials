package ir.moke.javaee.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
