package vn.elca.training.model.entity;

import vn.elca.training.model.enumType.RoleEnum;
import vn.elca.training.util.RoleAttributeConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Convert(converter = RoleAttributeConverter.class)
    @Column(unique = true)
    private RoleEnum role;

    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employees = new HashSet<>();

    public Role() {
    }

    public Role(RoleEnum role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
