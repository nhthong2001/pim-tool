package vn.elca.training.model.exception;

public class InvalidProjectNumberException extends Throwable {
    private Integer projectNumber;

    public InvalidProjectNumberException(String message, Integer projectNumber) {
        super(message + " Project Number =  " + projectNumber);
        this.projectNumber = projectNumber;
    }
    public InvalidProjectNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }
}
