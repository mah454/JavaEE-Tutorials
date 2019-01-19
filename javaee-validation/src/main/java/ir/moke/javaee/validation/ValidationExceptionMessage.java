package ir.moke.javaee.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMessage implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).type(MediaType.APPLICATION_JSON).build();
    }
}
