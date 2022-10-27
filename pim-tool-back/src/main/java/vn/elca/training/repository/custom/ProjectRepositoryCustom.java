package vn.elca.training.repository.custom;

import vn.elca.training.model.dto.SearchDataDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;

import java.util.List;
import java.util.Set;

public interface ProjectRepositoryCustom {

    List<Project> findAllProjectInfo();

    List<Project> findAllProjectByKeywordAndStatus(SearchDataDto searchDataDto);
}
