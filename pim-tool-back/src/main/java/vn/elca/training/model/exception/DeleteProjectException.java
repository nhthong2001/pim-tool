package vn.elca.training.model.exception;

public class DeleteProjectException extends Exception {
    private Long projectId;
    public DeleteProjectException(Long projectId) {
        super(String.format("Can't delete project which have status different from NEW!. Project ID = %s", projectId));
        this.projectId = projectId;
    }

    public DeleteProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public Long getProjectId() {
        return projectId;
    }
}