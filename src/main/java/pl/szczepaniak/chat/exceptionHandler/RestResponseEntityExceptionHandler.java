package pl.szczepaniak.chat.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.szczepaniak.chat.exceptions.EmailAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.NickAlreadyRegistered;
import pl.szczepaniak.chat.exceptions.UserNotFoundException;

import java.io.FileNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest request){
        String body = "user not found \n";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(body);
    }

    @ExceptionHandler(EmailAlreadyRegistered.class)
    protected ResponseEntity<Object> handleEmailRegistered(Exception ex, WebRequest request){
        String body = "email already registered \n";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(body);
    }

    @ExceptionHandler(NickAlreadyRegistered.class)
    protected ResponseEntity<Object> handleNickRegistered(Exception ex, WebRequest request){
        String body = "nick already registered \n";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(body);
    }

    @ExceptionHandler(FileNotFoundException.class)
    protected ResponseEntity<Object> handleFileNotFound(Exception ex, WebRequest request){
        String body = "file can't be found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
