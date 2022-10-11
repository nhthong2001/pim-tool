package vn.elca.training.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.Role;
import vn.elca.training.model.enumType.RoleEnum;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApplicationWebConfig.class)
public class GroupRepositoryTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testCountAll() {
        groupRepository.save(new Group());

        Assert.assertEquals(1, groupRepository.count());
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
        group1 = groupRepository.save(group1);


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
        groupRepository.save(group2);

    }
}