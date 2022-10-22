package vn.elca.training.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QEmployee;
import vn.elca.training.model.entity.QProject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
    @PersistenceContext
    private EntityManager em;


    @Override
    public Employee findByVisa(String visa) {
        var employee = QEmployee.employee;
        var project = QProject.project;

        return new JPAQuery<Employee>(em)
                .from(employee)
                .innerJoin(employee.projects, project).fetchJoin()
                .where(employee.visa.eq(visa))
                .fetchOne();
    }
}
