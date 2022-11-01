package vn.elca.training.model.exception;

public class InvalidGroupException extends Throwable {
    private String group;

    public InvalidGroupException(String group) {
        super(String.format("Invalid group with Leader Visa: %s.", group));
        this.group = group;
    }
    public InvalidGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getGroup() {
        return group;
    }
}
