package ro.sda.book_app.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.sda.book_app.model.ClientError;

import java.time.LocalDateTime;
import java.util.Collections;

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
                .timeStamp(LocalDateTime.now()).build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
