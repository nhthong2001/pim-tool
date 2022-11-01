package vn.elca.training.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.exception.InvalidGroupException;
import vn.elca.training.model.exception.InvalidProjectMemberException;
import vn.elca.training.model.exception.StartDateAfterEndDateException;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class ProjectValidator {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void validate(ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectMemberException, InvalidGroupException {

        if (groupRepository.findByGroupLeader_Visa(projectDto.getGroup()) == null) {
            throw new InvalidGroupException(projectDto.getGroup());
        }
        List<String> membersVisa = projectDto.getMember().stream()
                .filter(member -> !member.equals(""))
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        List<Employee> employeeList = employeeRepository.findAllByVisaIn(membersVisa);
        if (membersVisa.size() != employeeList.size()) {
            List<String> invalidVisas = new ArrayList<>();
            employeeList.forEach(e -> {
                if (!membersVisa.contains(e.getVisa())) {
                    invalidVisas.add(e.getVisa());
                }
            });
            throw new InvalidProjectMemberException(invalidVisas);
        }



        if (projectDto.getEndDate() != null && projectDto.getStartDate().isAfter(projectDto.getEndDate())) {
            throw new StartDateAfterEndDateException(projectDto.getEndDate(), projectDto.getStartDate());
        }

    }
}
