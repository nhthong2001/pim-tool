package vn.elca.training.model.exception;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExceptionDto {
    private final String message;
    private final HttpStatus httpStatus;

    public ApiExceptionDto(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
