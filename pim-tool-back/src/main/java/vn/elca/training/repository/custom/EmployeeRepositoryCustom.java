package vn.elca.training.repository.custom;

import vn.elca.training.model.entity.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    Employee findByVisa(String visa);
    List<Employee> findEmployeesByProjectId(Long projectId);

}
