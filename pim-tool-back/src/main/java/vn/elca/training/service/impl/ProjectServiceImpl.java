package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;

import java.util.List;
import java.util.Optional;

/**
 * @author vlp
 *
 */
@Service
@Profile("!dummy | dev")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

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
        return projectRepository.findByKeyword(keyword);
    }

    @Override
    public long count() {
        return projectRepository.count();
    }

    @Override
    public Project update(ProjectDto projectDto) {
        Project project = this.findById(projectDto.getId()).get();
        if (project == null){
            return null;
        }
        project.setName(projectDto.getName());
        project.setCustomer(projectDto.getCustomer());
        project.setFinishingDate(projectDto.getFinishingDate());

        return projectRepository.update(project);
    }
}
