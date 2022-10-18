package vn.elca.training.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private Employee groupLeader;
    @OneToMany(
            mappedBy = "group",
            orphanRemoval = true
    )
    private Set<Project> projects = new HashSet<>();

    @Column(name = "version")
    private Long version;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getGroupLeader() {
        return groupLeader;
    }

    public void addGroupLeader(Employee groupLeader) {
        this.groupLeader = groupLeader;
        groupLeader.getGroupsLead().add(this);
    }

    public void addProject(Project project) {
        this.projects.add(project);
        project.setGroup(this);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
        project.setGroup(null);
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
