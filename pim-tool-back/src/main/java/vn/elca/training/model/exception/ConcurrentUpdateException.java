package vn.elca.training.model.exception;

public class ConcurrentUpdateException extends Throwable {
    private String entityName;
    private Long id;

    public ConcurrentUpdateException(String entityName, Long id) {
        super("Concurrent Update Occur");
        this.entityName = entityName;
        this.id = id;
    }
    public ConcurrentUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getEntityName() {
        return entityName;
    }

    public Long getId() {
        return id;
    }
}
