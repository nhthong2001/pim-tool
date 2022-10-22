package vn.elca.training.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import vn.elca.training.model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<Employee> findEmployeeById(Long projectId) {
        Project project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .innerJoin(QProject.project.employees, QEmployee.employee).fetchJoin()
                .where(QProject.project.id.eq(projectId))
                .fetchOne();
        return project.getEmployees();
    }

    @Override
    public List<Project> findAllProjectInfo() {
        var group = QGroup.group;
        var project = QProject.project;
        var employee = QEmployee.employee;
        return new JPAQuery<Project>(em)
                .from(project)
                .innerJoin(project.group, group).fetchJoin()
                .innerJoin(group.groupLeader, employee).fetchJoin()
                .fetch();
    }
}
