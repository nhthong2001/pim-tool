package vn.elca.training.model.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}