package vn.elca.training.service;

import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;

import java.util.List;
import java.util.Optional;

/**
 * @author vlp
 */
public interface ProjectService {
    List<Project> findAll();

    Optional<Project> findById(Long id);

    List<Project> findByKeyword(String keyword);

    Optional<Project> findByName(String name);

    long count();

    Project update(ProjectDto projectDto);

    Project maintain(Long id);
}
