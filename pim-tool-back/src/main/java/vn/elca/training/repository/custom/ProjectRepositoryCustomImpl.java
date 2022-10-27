package vn.elca.training.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import vn.elca.training.model.dto.SearchDataDto;
import vn.elca.training.model.entity.*;

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
                .innerJoin(project.group, group).fetchJoin()
                .innerJoin(group.groupLeader, employee).fetchJoin()
                .fetch();
    }

    @Override
    public List<Project> findAllProjectByKeywordAndStatus(SearchDataDto searchDataDto) {
        QProject project = QProject.project;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (searchDataDto.getKeyword() != null) {
            try {
                booleanBuilder.or(project.projectNumber.eq(Integer.parseInt(searchDataDto.getKeyword())));
            } catch (NumberFormatException e){
            }
            booleanBuilder.or(project.customer.containsIgnoreCase(searchDataDto.getKeyword()));
            booleanBuilder.or(project.name.containsIgnoreCase(searchDataDto.getKeyword()));
        }
        if (searchDataDto.getStatus() != null ) {
            booleanBuilder.and(project.status.eq(searchDataDto.getStatus()));
        }
        return new JPAQuery<Project>(em)
                .from(project)
                .where(booleanBuilder)
                .orderBy(project.projectNumber.asc())
                .fetch();
    }
}
