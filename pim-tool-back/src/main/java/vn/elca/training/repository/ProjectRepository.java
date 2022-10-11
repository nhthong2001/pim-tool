package vn.elca.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import vn.elca.training.model.entity.Project;

import java.util.List;
import java.util.Optional;

/**
 * @author vlp
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, QuerydslPredicateExecutor<Project> {
    List<Project> findByNameContains(String keyword);
    Optional<Project> findByName(String name);

}
