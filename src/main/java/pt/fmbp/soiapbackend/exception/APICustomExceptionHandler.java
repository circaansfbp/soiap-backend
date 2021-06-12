package pt.fmbp.soiapbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class APICustomExceptionHandler extends ResponseEntityExceptionHandler  {
    private HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errorDetails = new ArrayList<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            errorDetails.add(objectError.getDefaultMessage());
        }

        APICustomException exception = new APICustomException("Uno o más argumentos ingresados son inválidos",
                errorDetails, badRequest);
        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errorDetails = new ArrayList<>();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            errorDetails.add(violation.getMessage());
        }

        APICustomException exception = new APICustomException("Error de restricción", errorDetails, badRequest);
        return new ResponseEntity<>(exception, badRequest);
    }
}
