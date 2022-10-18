package vn.elca.training.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.elca.training.ApplicationWebConfig;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.Role;
import vn.elca.training.model.enumType.RoleEnum;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApplicationWebConfig.class)
public class GroupRepositoryTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private  RoleRepository roleRepository;

    @Test
    public void testCountAll() {
        groupRepository.save(new Group());

        Assert.assertEquals(1, groupRepository.count());
    }

    @Test
    //@Transactional
    public void testSaveAll() {

        Role groupLeader = new Role(RoleEnum.GROUP_LEADER);
        roleRepository.save(groupLeader);
        Role pjLeader = new Role(RoleEnum.PROJECT_LEADER);
        roleRepository.save(pjLeader);
        Role developer = new Role(RoleEnum.DEVELOPER);
        roleRepository.save(developer);
        Role qualityAgent = new Role(RoleEnum.QUALITY_AGENT);
        roleRepository.save(qualityAgent);

        Employee qmv = new Employee("QMV");
        qmv.addRole(groupLeader);
        qmv.addRole(qualityAgent);
        employeeRepository.save(qmv);

        Employee hnh = new Employee("HNH");
        hnh.addRole(groupLeader);
        hnh.addRole(qualityAgent);
        employeeRepository.save(hnh);

        Employee htv = new Employee("HTV");
        htv.addRole(pjLeader);
        employeeRepository.save(htv);

        Employee qkp = new Employee("QKP");
        qkp.addRole(pjLeader);
        employeeRepository.save(qkp);

        Employee mkn = new Employee("MKN");
        mkn.addRole(pjLeader);
        employeeRepository.save(mkn);

        Employee apl = new Employee("APL");
        apl.addRole(pjLeader);
        employeeRepository.save(apl);

        Employee xhp = new Employee("XHP");
        xhp.addRole(pjLeader);
        employeeRepository.save(xhp);

        Employee tqp = new Employee("TQP");
        tqp.addRole(developer);
        employeeRepository.save(tqp);

        Employee nqn = new Employee("NQN");
        nqn.addRole(developer);
        employeeRepository.save(nqn);

        Employee hnl = new Employee("HNL");
        hnl.addRole(developer);
        employeeRepository.save(hnl);

        Employee tdn = new Employee("TDN");
        tdn.addRole(developer);
        employeeRepository.save(tdn);

        Employee hpn = new Employee("HPN");
        hpn.addRole(developer);
        employeeRepository.save(hpn);

        Employee bnn = new Employee("BNN");
        bnn.addRole(developer);
        employeeRepository.save(bnn);

        Employee pnh = new Employee("PNH");
        pnh.addRole(developer);
        employeeRepository.save(pnh);

        Employee vvt = new Employee("VVT");
        vvt.addRole(developer);
        employeeRepository.save(vvt);

        Employee plh = new Employee("PLH");
        plh.addRole(qualityAgent);
        employeeRepository.save(plh);

        Employee tbh = new Employee("TBH");
        tbh.addRole(qualityAgent);
        employeeRepository.save(tbh);

        Employee hun = new Employee("HUN");
        hun.addRole(qualityAgent);
        employeeRepository.save(hun);


        Project project1 = new Project("EFV");
        project1.addEmployee(htv);
        project1.addEmployee(tqp);
        project1.addEmployee(hnh);
        project1.addEmployee(nqn);
        projectRepository.save(project1);


        Project project2 = new Project("CXTRANET");
        project2.addEmployee(qkp);
        project2.addEmployee(plh);
        project2.addEmployee(hnl);
        projectRepository.save(project2);

        Project project3 = new Project("CRYSTAL BALL");
        //List<Employee> employees3 = Stream.of(mkn, tbh, tdn).collect(Collectors.toList());
        project3.addEmployee(mkn);
        project3.addEmployee(tbh);
        project3.addEmployee(tdn);
        projectRepository.save(project3);


        Group group1 = new Group("Group 1");
        group1.addGroupLeader(qmv);
        group1.addProject(project1);
        group1.addProject(project2);
        group1.addProject(project3);
        group1 = groupRepository.save(group1);
        project1.setGroup(group1);
        project2.setGroup(group1);
        project3.setGroup(group1);
        projectRepository.save(project1);
        projectRepository.save(project2);
        projectRepository.save(project3);



        Assert.assertEquals("Group 1", groupRepository.findById(group1.getId()).get().getName());


        Project project12 = new Project("IOC CLIENT EXTRANET");
        //List<Employee> employees12 = Stream.of(apl, hpn, hun, bnn, pnh).collect(Collectors.toList());
        project12.addEmployee(apl);
        project12.addEmployee(hpn);
        project12.addEmployee(hun);
        project12.addEmployee(bnn);
        project12.addEmployee(pnh);
        projectRepository.save(project12);

        Project project22 = new Project("KSTA MIGRATION");
        //List<Employee> employees22 = Stream.of(xhp, qmv, vvt).collect(Collectors.toList());
        project22.addEmployee(xhp);
        project22.addEmployee(qmv);
        project22.addEmployee(vvt);
        projectRepository.save(project22);

        Group group2 = new Group("Group 2");
        group2.addGroupLeader(hnh);
        group2.addProject(project12);
        group2.addProject(project22);
        groupRepository.save(group2);
        project12.setGroup(group2);
        project22.setGroup(group2);
        projectRepository.save(project12);
        projectRepository.save(project22);

        Assert.assertEquals(2, groupRepository.findAll().size());
    }
}