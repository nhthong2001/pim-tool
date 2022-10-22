package vn.elca.training.repository.custom;

import vn.elca.training.model.entity.Employee;

public interface EmployeeRepositoryCustom {
    Employee findByVisa(String visa);

}
