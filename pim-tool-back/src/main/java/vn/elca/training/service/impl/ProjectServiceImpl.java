package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author vlp
 */
@Service
@Profile("!dummy | dev")

public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> findByKeyword(String keyword) {
        return projectRepository.findByNameContains(keyword);
    }

    @Override
    public Optional<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public long count() {
        return projectRepository.count();
    }

    @Override
    public Project update(ProjectDto projectDto) {
        Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<ProjectDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }

        return projectRepository.findById(projectDto.getId()).map(projectUpdate -> {
            projectUpdate.setName(projectDto.getProjectName());
            projectUpdate.setCustomer(projectDto.getCustomer());
            projectUpdate.setEndDate(projectDto.getEndDate());
            return projectRepository.save(projectUpdate);
        }).orElse(null);
    }

    @Override
    public Project saveProject(Project project) {


        return projectRepository.save(project);
    }

    @Override
    public List<String> getListEmployee(Long projectId) {
        Set<Employee> employeeList = projectRepository.findEmployeeById(projectId);
        return employeeList
                .stream()
                .map(employee -> employee.getVisa())
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findAllProjectInfo() {
        return projectRepository.findAllProjectInfo();
    }

}
