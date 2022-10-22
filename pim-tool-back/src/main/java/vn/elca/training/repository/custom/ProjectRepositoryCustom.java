package vn.elca.training.repository.custom;

import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;

import java.util.List;
import java.util.Set;

public interface ProjectRepositoryCustom {
    Set<Employee> findEmployeeById(Long projectId);

    List<Project> findAllProjectInfo();
}
