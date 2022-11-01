package vn.elca.training.service;

import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.dto.SearchDataDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.*;

import java.util.List;
import java.util.Optional;

/**
 * @author vlp
 */
public interface ProjectService {
    Optional<Project> findById(Long id) throws ProjectNotFoundException;

    Project saveProject(ProjectDto projectDto) throws StartDateAfterEndDateException, ProjectNotFoundException, InvalidProjectMemberException, InvalidGroupException, InvalidProjectNumberException;

    List<String> getListEmployee(Long id);

    List<Project> findAllProjectInfo();

    Boolean isValidProjectNumber(Integer projectNumber);

    List<Project> searchProject(SearchDataDto searchDataDto);

    List<Project> searchProject(String keyword);

    Long deleteProject(Long id) throws ProjectNotFoundException, DeleteProjectException;

    List<Long> deleteListProject(List<Long> listId);

    Project updateProject(ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectMemberException, InvalidProjectNumberException, ProjectNotFoundException, InvalidGroupException;
}
