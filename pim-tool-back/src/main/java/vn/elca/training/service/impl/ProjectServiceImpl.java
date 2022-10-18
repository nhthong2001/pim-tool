package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
            projectUpdate.setName(projectDto.getName());
            projectUpdate.setCustomer(projectDto.getCustomer());
            projectUpdate.setFinishingDate(projectDto.getFinishingDate());
            return projectRepository.save(projectUpdate);
        }).orElse(null);
    }

    @Override
    @Transactional
    public Project maintain(Long id) {
        return projectRepository.findById(id).map(project -> {
            project.setActivated(false);
            projectRepository.save(project);

            String newPjName = project.getName() + "Maint." + LocalDate.now().getYear();
            Project maintainPj = new Project(newPjName);
            projectRepository.save(maintainPj);
            return project;
        }).orElse(null);
    }
}
