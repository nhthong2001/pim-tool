package vn.elca.training.model.exception;

public class ProjectNotFoundException extends RuntimeException{
    private long projectId;
    public ProjectNotFoundException(long projectId) {
        this.projectId = projectId;
    }
    public long getProjectId() {
        return projectId;
    }
}
