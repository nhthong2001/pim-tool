package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.ProjectStatus;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.dto.SearchDataDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.*;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.ApplicationMapper;
import vn.elca.training.validator.ProjectValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vlp
 */
@Service
@Profile("!dummy | dev")
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ApplicationMapper mapper;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private ProjectValidator projectValidator;

    @Override
    public Optional<Project> findById(Long id) throws ProjectNotFoundException {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new ProjectNotFoundException("Project is not exist!", id);
        }
        return project;
    }

    @Override
    public Project saveProject(ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectMemberException, InvalidGroupException, InvalidProjectNumberException {
        if (!this.isValidProjectNumber(projectDto.getProjectNumber())) {
            throw new InvalidProjectNumberException("Project Number is not valid.", projectDto.getProjectNumber());
        }
        projectValidator.validate(projectDto);

        Project project = mapper.projectDtoToProject(projectDto);


        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectMemberException, InvalidProjectNumberException, ProjectNotFoundException, InvalidGroupException {
        try {
            Optional<Project> project = this.findById(projectDto.getId());
            if (project.get().getProjectNumber().intValue() != projectDto.getProjectNumber().intValue()) {
                throw new InvalidProjectNumberException("Project Number can not modified.", projectDto.getProjectNumber());
            }
        } catch (ProjectNotFoundException e) {
            throw new ProjectNotFoundException("Can't find project for update!", e.getId());
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
    public List<Project> searchProject(SearchDataDto searchDataDto) {
        return projectRepository.findAllProjectByKeywordAndStatus(searchDataDto);
    }

    @Override
    public List<Project> searchProject(String keyword) {
        return projectRepository.findByNameContainsIgnoreCaseOrderByProjectNumberAsc(keyword);
    }

    @Override
    public Long deleteProject(Long id) throws ProjectNotFoundException, DeleteProjectException {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new ProjectNotFoundException("Can't find project for delete!", id);
        } else {
            if (project.get().getStatus() != ProjectStatus.NEW) {
                throw new DeleteProjectException(id);
            }
        }
        projectRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Long> deleteListProject(List<Long> listId)  {
        // Throw EmptyResultDataAccessException when have project id not found in db
        List<Project> projects = projectRepository.findAllByIdIn(listId);



        listId.forEach(id -> projectRepository.deleteById(id));
        return listId;
    }

}
