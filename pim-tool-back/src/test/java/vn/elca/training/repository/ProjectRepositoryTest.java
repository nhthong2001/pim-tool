package vn.elca.training.repository;

import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.model.entity.*;
import vn.elca.training.model.enumType.RoleEnum;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@ContextConfiguration(classes = {ApplicationWebConfig.class})
//@RunWith(value=SpringRunner.class)

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApplicationWebConfig.class)
public class ProjectRepositoryTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testCountAll() {
        projectRepository.save(new Project("KSTA", LocalDate.now()));
        projectRepository.save(new Project("LAGAPEO", LocalDate.now()));
        projectRepository.save(new Project("ZHQUEST", LocalDate.now()));
        projectRepository.save(new Project("SECUTIX", LocalDate.now()));
        Assert.assertEquals(10, projectRepository.count());
    }

    @Test
    public void testFindOneWithQueryDSL() {
        final String PROJECT_NAME = "KSTA";
        projectRepository.save(new Project(PROJECT_NAME, LocalDate.now()));
        Project project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.name.eq(PROJECT_NAME))
                .fetchFirst();
        Assert.assertEquals(PROJECT_NAME, project.getName());
    }

    @Test
    public void findByNameContainsTest() {
        List<Project> projectList = projectRepository.findByNameContains("CXT");

        final String PROJECT_NAME = "CXTRANET";


        Assert.assertEquals(PROJECT_NAME, projectList.get(0).getName());

    }

    @Test
    public void testSaveOne() {
        Role groupLeader = new Role(RoleEnum.GROUP_LEADER);
        Role pjLeader = new Role(RoleEnum.PROJECT_LEADER);
        Role developer = new Role(RoleEnum.DEVELOPER);
        Role qualityAgent = new Role(RoleEnum.QUALITY_AGENT);

        Employee qmv = new Employee("QMV");
        qmv.addRole(groupLeader);

        Employee hnh = new Employee("HNH");
        hnh.addRole(qualityAgent);

        Employee htv = new Employee("HTV");
        htv.addRole(pjLeader);


        Employee tqp = new Employee("TQP");
        tqp.addRole(developer);

        Employee nqn = new Employee("NQN");
        nqn.addRole(developer);

        Group group1 = new Group("Group 1");
        group1.addGroupLeader(qmv);

        Project project1 = new Project("Save One");
        project1.addEmployee(htv);
        project1.addEmployee(tqp);
        project1.addEmployee(hnh);
        project1.addEmployee(nqn);

        project1.addGroup(group1);

        projectRepository.save(project1);

        Assert.assertEquals(7, projectRepository.count());
    }

    @Test
    @Transactional
    public void testSaveAll() {

        Role groupLeader = new Role(RoleEnum.GROUP_LEADER);
        Role pjLeader = new Role(RoleEnum.PROJECT_LEADER);
        Role developer = new Role(RoleEnum.DEVELOPER);
        Role qualityAgent = new Role(RoleEnum.QUALITY_AGENT);

        Employee qmv = new Employee("QMV");
        qmv.addRole(groupLeader);

        Employee hnh = new Employee("HNH");
        hnh.addRole(groupLeader);

        Employee htv = new Employee("HTV");
        htv.addRole(pjLeader);

        Employee qkp = new Employee("QKP");
        qkp.addRole(pjLeader);

        Employee mkn = new Employee("MKN");
        mkn.addRole(pjLeader);

        Employee apl = new Employee("APL");
        apl.addRole(pjLeader);

        Employee xhp = new Employee("XHP");
        xhp.addRole(pjLeader);

        Employee tqp = new Employee("TQP");
        tqp.addRole(developer);

        Employee nqn = new Employee("NQN");
        nqn.addRole(developer);

        Employee hnl = new Employee("HNL");
        hnl.addRole(developer);

        Employee tdn = new Employee("TDN");
        tdn.addRole(developer);

        Employee hpn = new Employee("HPN");
        hpn.addRole(developer);

        Employee bnn = new Employee("BNN");
        bnn.addRole(developer);

        Employee pnh = new Employee("PNH");
        pnh.addRole(developer);

        Employee vvt = new Employee("VVT");
        vvt.addRole(developer);

        Employee plh = new Employee("PLH");
        plh.addRole(qualityAgent);

        Employee tbh = new Employee("TBH");
        tbh.addRole(qualityAgent);

        Employee hun = new Employee("HUN");
        hun.addRole(qualityAgent);

        hnh.addRole(qualityAgent);
        qmv.addRole(qualityAgent);


        Project project1 = new Project("EFV");
        project1.addEmployee(htv);
        project1.addEmployee(tqp);
        project1.addEmployee(hnh);
        project1.addEmployee(nqn);


        Project project2 = new Project("CXTRANET");
        project2.addEmployee(qkp);
        project2.addEmployee(plh);
        project2.addEmployee(hnl);

        Project project3 = new Project("CRYSTAL BALL");
        //List<Employee> employees3 = Stream.of(mkn, tbh, tdn).collect(Collectors.toList());
        project3.addEmployee(mkn);
        project3.addEmployee(tbh);
        project3.addEmployee(tdn);


        Group group1 = new Group("Group 1");
        group1.addGroupLeader(qmv);
        group1.addProject(project1);
        group1.addProject(project2);
        group1.addProject(project3);


        Project project12 = new Project("IOC CLIENT EXTRANET");
        //List<Employee> employees12 = Stream.of(apl, hpn, hun, bnn, pnh).collect(Collectors.toList());
        project12.addEmployee(apl);
        project12.addEmployee(hpn);
        project12.addEmployee(hun);
        project12.addEmployee(bnn);
        project12.addEmployee(pnh);

        Project project22 = new Project("KSTA MIGRATION");
        //List<Employee> employees22 = Stream.of(xhp, qmv, vvt).collect(Collectors.toList());
        project22.addEmployee(xhp);
        project22.addEmployee(qmv);
        project22.addEmployee(vvt);

        Group group2 = new Group("Group 2");
        group2.addGroupLeader(hnh);
        group2.addProject(project12);
        group2.addProject(project22);


        List<Project> allProject = Stream.of(project1, project2, project3, project12, project22).collect(Collectors.toList());


        projectRepository.saveAll(allProject);

        Assert.assertEquals(11, projectRepository.count());
    }

    @Test
    public void testSimpleQuery(){
        Project project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .where(QProject.project.name.eq("IOC CLIENT EXTRANET"))
                .fetchOne();
        Assert.assertEquals("IOC CLIENT EXTRANET", project.getName());
    }

    @Test
    public void testComplexQuery(){
        Role groupLeader = new Role(RoleEnum.GROUP_LEADER);
        Role pjLeader = new Role(RoleEnum.PROJECT_LEADER);
        Role developer = new Role(RoleEnum.DEVELOPER);
        Role qualityAgent = new Role(RoleEnum.QUALITY_AGENT);

        Employee qmv = new Employee("QMV");
        qmv.addRole(groupLeader);

        Employee hnh = new Employee("HNH");
        hnh.addRole(qualityAgent);

        Employee htv = new Employee("HTV");
        htv.addRole(pjLeader);


        Employee tqp = new Employee("TQP");
        tqp.addRole(developer);

        Employee nqn = new Employee("NQN");
        nqn.addRole(developer);

        Group group1 = new Group("Group 1");
        group1.addGroupLeader(qmv);

        Project project1 = new Project("Complex Project");
        project1.addEmployee(htv);
        project1.addEmployee(tqp);
        project1.addEmployee(hnh);
        project1.addEmployee(nqn);

        project1.addGroup(group1);

        projectRepository.save(project1);

        Project project = new JPAQuery<Project>(em)
                .from(QProject.project)
                .innerJoin(QGroup.group)
                .on(QProject.project.group.name.eq(QGroup.group.name))
                .where(
                        QGroup.group.name.eq("Group 1"),
                        QProject.project.employees.size().eq(4),
                        QProject.project.name.eq("Complex Project")
                )

                .fetchOne();
        Assert.assertEquals("Complex Project", project.getName());
    }

}
