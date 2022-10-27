package vn.elca.training.service;

import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.InvalidProjectInfoException;
import vn.elca.training.model.exception.NotFoundException;
import vn.elca.training.model.exception.StartDateAfterEndDateException;

import java.util.List;
import java.util.Optional;

/**
 * @author vlp
 */
public interface ProjectService {
    List<Project> findAll();

    Optional<Project> findById(Long id) throws NotFoundException;

    List<Project> findByKeyword(String keyword);

    Optional<Project> findByName(String name);

    long count();

    Project saveProject(ProjectDto projectDto) throws StartDateAfterEndDateException, NotFoundException, InvalidProjectInfoException;

    List<String> getListEmployee(Long id);

    List<Project> findAllProjectInfo();

    Boolean isValidProjectNumber(Integer projectNumber);

    List<Project> searchProject(String keyword, String status);

    Long deleteProject(Long id);

    List<Long> deleteListProject(List<Long> listId);

    Project updateProject(ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectInfoException;
}
