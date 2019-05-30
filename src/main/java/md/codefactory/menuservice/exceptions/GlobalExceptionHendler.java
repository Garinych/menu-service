package md.codefactory.menuservice.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@ControllerAdvice
@RestController
public class GlobalExceptionHendler extends ResponseEntityExceptionHandler {

    private String errorMessage = "errorMessage";

    @ExceptionHandler({MenuAlreadyExistException.class})
    public ResponseEntity<Map<String, String>> menuAlreadyExist(MenuAlreadyExistException e) {
        Map<String, String> responce = new HashMap<>();
        responce.put(errorMessage, e.getMessage());
        return new ResponseEntity<>(responce, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({MenuNotFoundException.class})
    public ResponseEntity<Map<String, String>> menuNotFound(MenuNotFoundException e) {
        Map<String, String> responce = new HashMap<>();
        responce.put(errorMessage, e.getMessage());
        return new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({FoodAlreadyExistException.class})
    public ResponseEntity<Map<String, String>> foodAlreadyExist(FoodAlreadyExistException e) {
        Map<String, String> responce = new HashMap<>();
        responce.put(errorMessage, e.getMessage());
        return new ResponseEntity<>(responce, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({FoodNotFoundException.class})
    public ResponseEntity<Map<String, String>> foodNotFound(FoodNotFoundException e) {
        Map<String, String> responce = new HashMap<>();
        responce.put(errorMessage, e.getMessage());
        return new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> error = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));

        return new ResponseEntity<>(error, headers, status);
    }

}
