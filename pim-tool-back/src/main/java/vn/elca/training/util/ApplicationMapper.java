package vn.elca.training.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gtn
 */
@Component
public class ApplicationMapper {
    public ApplicationMapper() {
        // Mapper utility class
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GroupRepository groupRepository;


    public ProjectDto projectToFullInfoProjectDto(Project entity, List<String> members) {

        return ProjectDto.builder()
                .id(entity.getId())
                .projectNumber(entity.getProjectNumber())
                .projectName(entity.getName())
                .customer(entity.getCustomer())
                .group(entity.getGroup().getGroupLeader().getVisa())
                .member(members)
                .status(entity.getStatus())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .version(entity.getVersion())
                .build();
    }

    public ProjectDto projectToProjectDto(Project entity) {
        return ProjectDto.builder()
                .id(entity.getId())
                .projectNumber(entity.getProjectNumber())
                .projectName(entity.getName())
                .status(entity.getStatus())
                .customer(entity.getCustomer())
                .startDate(entity.getStartDate())
                .build();
    }


    public Project projectDtoToProject(ProjectDto projectDto) {
        Project project = new Project();

        project.setId(projectDto.getId());

        project.setProjectNumber(projectDto.getProjectNumber());

        project.setName(projectDto.getProjectName());

        project.setCustomer(projectDto.getCustomer());

        Group group = groupRepository.findByGroupLeader_Visa(projectDto.getGroup());
        project.setGroup(group);

        project.setStatus(projectDto.getStatus());

        project.setStartDate(projectDto.getStartDate());

        if (projectDto.getEndDate() != null){
            project.setEndDate(projectDto.getEndDate());
        }
        project.setVersion(projectDto.getVersion());
        List<String> membersVisa = projectDto.getMember().stream().map(String::trim).collect(Collectors.toList());
        List<Employee> employeeList = employeeRepository.findAllByVisaIn(membersVisa);
        employeeList.forEach(employee -> project.getEmployees().add(employee));

        return project;
    }


    public GroupDto groupToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setLeaderId(group.getGroupLeader().getId());
        groupDto.setLeaderVisa(group.getGroupLeader().getVisa());
        return groupDto;
    }

    public EmployeeDto employeeToEmployeeDto(Employee employee) {

        return new EmployeeDto(employee.getVisa(), employee.getFirstName() + employee.getLastName());
    }
}
