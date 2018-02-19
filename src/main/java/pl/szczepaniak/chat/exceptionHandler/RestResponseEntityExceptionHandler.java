package pl.szczepaniak.chat.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request){
        String body = "user not found \n";
        body += request.toString();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(body);
    }
}
