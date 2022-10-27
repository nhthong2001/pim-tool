package vn.elca.training.web.exception;

import org.hibernate.StaleObjectStateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.elca.training.model.exception.ApiException;
import vn.elca.training.model.exception.InvalidProjectInfoException;
import vn.elca.training.model.exception.StartDateAfterEndDateException;
import vn.elca.training.model.exception.NotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlerCustom {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleProjectNotFound(NotFoundException e){
        ApiException projectNotFoundException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(projectNotFoundException, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {StaleObjectStateException.class})
    public ResponseEntity<Object> handleConcurrentUpdate(StaleObjectStateException e) {
        ApiException apiException = new ApiException(
                "Concurrent Update Error!",
                HttpStatus.CONFLICT,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(DataIntegrityViolationException e) {
        ApiException apiException = new ApiException(
                "Project Number Already Have!",
                HttpStatus.CONFLICT,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = {StartDateAfterEndDateException.class})
    public ResponseEntity<Object> handleDateEndException(StartDateAfterEndDateException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {InvalidProjectInfoException.class})
    public ResponseEntity<Object> handleProjectInfoException(InvalidProjectInfoException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
