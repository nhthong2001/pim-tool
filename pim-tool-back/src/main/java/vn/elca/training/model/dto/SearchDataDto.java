package vn.elca.training.model.dto;

import vn.elca.training.model.ProjectStatus;

public class SearchDataDto {
    private String keyword;
    private ProjectStatus status;

    public SearchDataDto() {
    }

    public SearchDataDto(String keyword, ProjectStatus status) {
        this.keyword = keyword;
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
