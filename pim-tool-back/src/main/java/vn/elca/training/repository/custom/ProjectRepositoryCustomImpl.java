package vn.elca.training.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import vn.elca.training.model.dto.SearchDataDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QEmployee;
import vn.elca.training.model.entity.QGroup;
import vn.elca.training.model.entity.QProject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Project> findAllProjectInfo() {
        QGroup group = QGroup.group;
        QProject project = QProject.project;
        QEmployee employee = QEmployee.employee;
        return new JPAQuery<Project>(em)
                .from(project)
                .orderBy(project.projectNumber.asc())
                .innerJoin(project.group, group).fetchJoin()
                .innerJoin(group.groupLeader, employee).fetchJoin()
                .fetch();
    }

    @Override
    public List<Project> findAllProjectByKeywordAndStatus(SearchDataDto searchDataDto) {
        QProject project = QProject.project;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!StringUtils.isEmpty(searchDataDto.getKeyword())) {
            try {
                booleanBuilder.or(project.projectNumber.eq(Integer.parseInt(searchDataDto.getKeyword())));
            } catch (NumberFormatException e) {
                // keyword could be a string
            }
            booleanBuilder.or(project.customer.containsIgnoreCase(searchDataDto.getKeyword()));
            booleanBuilder.or(project.name.containsIgnoreCase(searchDataDto.getKeyword()));
        }
        if (searchDataDto.getStatus() != null) {
            booleanBuilder.and(project.status.eq(searchDataDto.getStatus()));
        }
        return new JPAQuery<Project>(em)
                .from(project)
                .where(booleanBuilder)
                .orderBy(project.projectNumber.asc())
                .fetch();
    }
}
