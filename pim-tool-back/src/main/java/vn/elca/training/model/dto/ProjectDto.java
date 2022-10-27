package vn.elca.training.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author gtn
 *
 */
@Builder
public class ProjectDto {
    private Long id;

   
    @PositiveOrZero(message = "Project Number must be a positive number")
    private Integer projectNumber;

    @NotEmpty(message = "Name must not be empty")
    private String projectName;

    @NotEmpty(message = "Customer must not be empty")
    private String customer;
    @NotEmpty(message = "Group must not be empty")
    private String group;

    private List<String> member;
    @NotEmpty(message = "Status must not be empty")
    private String status;

    @NotEmpty(message = "Start Date must not be empty")
    private String startDate;

    private String endDate;

    private  Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getMember() {
        return member;
    }

    public void setMember(List<String> member) {
        this.member = member;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
