package vn.elca.training.service;

import java.util.List;
import java.util.Optional;

import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;

/**
 * @author vlp
 *
 */
public interface ProjectService {
    List<Project> findAll();
    Optional<Project> findById(Long id);
    List<Project> findByKeyword(String keyword);
    long count();

    Project update(ProjectDto projectDto);
}
