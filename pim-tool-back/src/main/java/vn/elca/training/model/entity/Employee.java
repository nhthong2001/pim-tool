package vn.elca.training.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "visa", nullable = false)
    @Length(max = 4)
    private String visa;
    @ManyToMany(mappedBy = "employees")
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "groupLeader",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Group> groupsLead = new HashSet<>();

    public Employee() {
    }

    public Employee(String visa) {
        this.visa = visa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void addRole(Role role) {
        roles.add(role);
        role.getEmployees().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getEmployees().remove(this);
    }
    public Set<Group> getGroupsLead() {
        return groupsLead;
    }



    public void setGroupsLead(Set<Group> groupsLead) {
        this.groupsLead = groupsLead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(visa, employee.visa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visa);
    }
}
