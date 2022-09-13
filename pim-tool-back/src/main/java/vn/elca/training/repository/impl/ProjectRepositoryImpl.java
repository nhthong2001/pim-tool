package vn.elca.training.repository.impl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QProject;
import vn.elca.training.model.entity.QTask;
import vn.elca.training.repository.ProjectRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Project> findAll() {
        return new JPAQuery<Project>(em)
                .from(QProject.project)
                .fetch();
    }

    @Override
    public List<Project> findByKeyword(String keyword) {
        return new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.name.contains(keyword))
                .fetch();
    }

    @Override
    @Transactional
    public Project update(Project project) {
        QProject qProject = QProject.project;
        new JPAUpdateClause(em, qProject).where(qProject.id.eq(project.getId()))
                .set(qProject.name, project.getName())
                .set(qProject.customer, project.getCustomer())
                .set(qProject.finishingDate, project.getFinishingDate())
                .execute();
        return project;
    }


    @Override
    public List<Project> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Project> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Project project) {

    }

    @Override
    public void deleteAll(Iterable<? extends Project> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Project> S save(S s) {
        return null;
    }

    @Override
    public <S extends Project> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return Optional.ofNullable(new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.id.eq(id))
                .fetchOne());
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Project> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Project> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Project getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Project> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Project> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Project> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Project> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<Project> findOne(Predicate predicate) {
        return Optional.empty();
    }

    @Override
    public Iterable<Project> findAll(Predicate predicate) {
        return null;
    }

    @Override
    public Iterable<Project> findAll(Predicate predicate, Sort sort) {
        return null;
    }

    @Override
    public Iterable<Project> findAll(Predicate predicate, OrderSpecifier<?>... orderSpecifiers) {
        return null;
    }

    @Override
    public Iterable<Project> findAll(OrderSpecifier<?>... orderSpecifiers) {
        return null;
    }

    @Override
    public Page<Project> findAll(Predicate predicate, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Predicate predicate) {
        return 0;
    }

    @Override
    public boolean exists(Predicate predicate) {
        return false;
    }
}
