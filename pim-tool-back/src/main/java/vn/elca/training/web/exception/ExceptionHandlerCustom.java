package vn.elca.training.web.exception;

import org.hibernate.StaleObjectStateException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.elca.training.model.exception.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ProjectNotFoundException.class})
    public ResponseEntity<Object> handleProjectNotFound(ProjectNotFoundException e) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {StaleObjectStateException.class})
    public ResponseEntity<Object> handleConcurrentUpdate(StaleObjectStateException e) {
        ConcurrentUpdateException concurrentUpdateException = new ConcurrentUpdateException(e.getEntityName(), (Long) e.getIdentifier());
        return new ResponseEntity<>(concurrentUpdateException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {InvalidProjectNumberException.class})
    public ResponseEntity<Object> handleConstraintViolationException(InvalidProjectNumberException e) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(e.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {StartDateAfterEndDateException.class})
    public ResponseEntity<Object> handleDateEndException(StartDateAfterEndDateException e) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {InvalidProjectMemberException.class})
    public ResponseEntity<Object> handleProjectInfoException(InvalidProjectMemberException e) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {DeleteProjectException.class})
    public ResponseEntity<Object> handleDeleteException(DeleteProjectException e) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(e.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleDeleteListException(EmptyResultDataAccessException e) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(e.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.FORBIDDEN);
    }

}
