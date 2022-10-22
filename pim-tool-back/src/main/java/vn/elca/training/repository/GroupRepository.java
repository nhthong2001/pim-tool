package vn.elca.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import vn.elca.training.model.entity.Group;
import vn.elca.training.repository.custom.GroupRepositoryCustom;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, QuerydslPredicateExecutor<Group>, GroupRepositoryCustom {
    Group findByGroupLeader_Visa(String leaderVisa);
}
