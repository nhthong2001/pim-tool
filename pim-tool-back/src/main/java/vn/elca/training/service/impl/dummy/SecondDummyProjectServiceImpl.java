package vn.elca.training.service.impl.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;

import java.util.List;
import java.util.Optional;

/**
 * @author gtn
 *
 */
@Service("secondDummyProjectServiceImpl")
@Profile("dummy")
public class SecondDummyProjectServiceImpl extends AbstractDummyProjectService implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public List<Project> findAll() {
        throw new UnsupportedOperationException("This is second dummy service");
    }

    @Override
    public Optional<Project> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Project> findByKeyword(String keyword) {
        return null;
    }

    @Override
    public long count() {
        printCurrentActiveProfiles();
        throw new UnsupportedOperationException("This is second dummy service");
    }

    @Override
    public Project update(ProjectDto projectDto) {
        Optional<Project> project = projectRepository.findById(projectDto.getId());
        if (project.isEmpty()){
            return null;
        }
        Project projectUpdate = project.get();

        projectUpdate.setName(projectDto.getName());
        projectUpdate.setCustomer(projectDto.getCustomer());
        projectUpdate.setFinishingDate(projectDto.getFinishingDate());

        return projectRepository.update(projectUpdate);
    }
}
