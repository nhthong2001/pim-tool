package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends AbstractApplicationController{
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getListEmployee() {
        List<Employee> employeeList = employeeService.findAll();
        return employeeList
                .stream()
                .map(mapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }
}
