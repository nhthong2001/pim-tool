package vn.elca.training.model.exception;

public class ProjectNotFoundException extends Exception {
    Long id;

    public ProjectNotFoundException(String message, Long id) {
        super(message + " Project Id = " + id);
        this.id = id;
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public Long getId() {
        return id;
    }
}