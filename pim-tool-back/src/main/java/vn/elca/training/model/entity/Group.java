package vn.elca.training.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "groups")
@Table(name = "groups")
public class Group extends AbstractEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_leader_id")
    private Employee groupLeader;

    @OneToMany(mappedBy = "group")
    private Set<Project> projects = new HashSet<>();


    public Group() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
