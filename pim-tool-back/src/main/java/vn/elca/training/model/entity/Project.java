package vn.elca.training.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import vn.elca.training.model.ProjectStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author vlp
 */
@Entity
public class Project extends AbstractEntity{


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(unique = true, nullable = false)
    private Integer projectNumber;

    @Column(nullable = false)
    private String name;

    @Column
    private String customer;

    @Enumerated(EnumType.STRING)
    @Column(length = 3, nullable = false)
    private ProjectStatus status;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endDate;



    @ManyToMany
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();


    public Project(String name) {
        this.name = name;
    }

    public Project(String name, LocalDate finishingDate) {
        this.name = name;
        this.endDate = finishingDate;
    }

    public Project(Long id, String name, LocalDate finishingDate) {
        this.id = id;
        this.name = name;
        this.endDate = finishingDate;
    }

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }



    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        Project project = (Project) o;
        if (this == o) {
            if (this.id == project.getId() && this.name == project.getName()) {
                return true;
            }
        }

        if (o == null || getClass() != o.getClass()) return false;


        return new EqualsBuilder().append(id, project.id).append(group, project.group).isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}