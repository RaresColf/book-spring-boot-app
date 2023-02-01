package ro.sda.book_app.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.sda.book_app.model.ClientError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ClientError> handleNotFoundEx(NotFoundException exception) {
        log.error("Exception occurred with message: {}", exception.getMessage(), exception);
        ClientError response = ClientError.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message("Not found in the database")
                .errors(Collections.singletonList(exception.getLocalizedMessage()))
                .timeStamp(new Date(System.currentTimeMillis()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ClientError> handleValidationException (MethodArgumentNotValidException exception)
    {
        log.error("Validation exception occurred {}", exception.getMessage());
//        List<FieldError> errorList = exception.getFieldErrors();
//        List<String> resultList = new ArrayList<>();
//
//        for (FieldError err : errorList) {
//            resultList.add(err.getDefaultMessage());
//        }

        List<String> list = exception.getFieldErrors().stream().map((FieldError e) -> e.getDefaultMessage()).toList();

        ClientError response = ClientError.builder()
                .statusCode(400)
                .message("constraint violation")
                .timeStamp(new Date(System.currentTimeMillis()))
                .errors(list)
                .build();

        return new ResponseEntity<ClientError>(response, HttpStatus.BAD_REQUEST);
    }


}
