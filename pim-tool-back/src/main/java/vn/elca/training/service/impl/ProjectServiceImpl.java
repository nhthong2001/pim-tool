package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import vn.elca.training.model.ProjectStatus;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.dto.SearchDataDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.InvalidProjectInfoException;
import vn.elca.training.model.exception.NotFoundException;
import vn.elca.training.model.exception.StartDateAfterEndDateException;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.GroupRepository;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.ApplicationMapper;
import vn.elca.training.util.Converter;
import vn.elca.training.validator.ProjectValidator;

import java.util.List;
import java.util.Optional;
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
    private ApplicationMapper mapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProjectValidator projectValidator;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(Long id) throws NotFoundException {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("Project with id = " + id + "is not exist!");
        }
        return project;
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
    public Project saveProject(ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectInfoException {
        if (!this.isValidProjectNumber(projectDto.getProjectNumber())) {
            throw new InvalidProjectInfoException("Project Number is not valid!");
        }
        projectValidator.validate(projectDto);

        Project project = mapper.projectDtoToProject(projectDto);


        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectInfoException {
        try {
            Optional<Project> project = this.findById(projectDto.getId());
            if (project.get().getProjectNumber() != projectDto.getProjectNumber()) {
                throw new InvalidProjectInfoException("Project Number is not be modified!");
            }
        } catch (NotFoundException e) {
            throw new InvalidProjectInfoException("Project Id is not valid!");
        }
        projectValidator.validate(projectDto);
        Project project = mapper.projectDtoToProject(projectDto);

        return projectRepository.save(project);
    }

    @Override
    public List<String> getListEmployee(Long projectId) {
        List<Employee> employeeList = employeeRepository.findEmployeesByProjectId(projectId);

        return employeeList
                .stream()
                .map(employee -> employee.getVisa())
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findAllProjectInfo() {
        return projectRepository.findAllProjectInfo();
    }

    @Override
    public Boolean isValidProjectNumber(Integer projectNumber) {
        if (projectRepository.findByProjectNumber(projectNumber).isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public List<Project> searchProject(String keyword, String status) {
        SearchDataDto searchDataDto = new SearchDataDto();
        searchDataDto.setKeyword(keyword);
        searchDataDto.setStatus(Converter.status(status));

        return projectRepository.findAllProjectByKeywordAndStatus(searchDataDto);
    }

    @Override
    public Long deleteProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            //throw new Exception("Can't find project for delete");
        } else {
            if (project.get().getStatus() != ProjectStatus.NEW) {
                // throw exception
            }
        }
        projectRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Long> deleteListProject(List<Long> listId) {
        listId.forEach(id -> projectRepository.deleteById(id));
        return listId;
    }

}
