package vn.elca.training.model.exception;

public class InvalidProjectInfoException extends Exception {

    public InvalidProjectInfoException(String message) {
        super(message);
    }

    public InvalidProjectInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}