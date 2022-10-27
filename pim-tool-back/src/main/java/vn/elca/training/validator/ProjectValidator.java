package vn.elca.training.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.exception.InvalidProjectInfoException;
import vn.elca.training.model.exception.NotFoundException;
import vn.elca.training.model.exception.StartDateAfterEndDateException;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.GroupRepository;
import vn.elca.training.service.ProjectService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectValidator {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void validate(ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectInfoException {

        if (groupRepository.findByGroupLeader_Visa(projectDto.getGroup()) == null) {
            throw new InvalidProjectInfoException("Group is not valid!");
        }
        List<String> membersVisa = projectDto.getMember().stream()
                .filter(member -> !member.equals(""))
                .map(String::trim)
                .collect(Collectors.toList());
        List<Employee> employeeList = employeeRepository.findAllByVisaIn(membersVisa);

        if (membersVisa.size() != employeeList.size()) {
            throw new InvalidProjectInfoException("List members is not valid!");
        }

        LocalDate startDate;
        try {
            startDate = LocalDate.parse(projectDto.getStartDate());
        } catch (DateTimeParseException e) {
            throw new InvalidProjectInfoException("Start Date is not valid!");
        }

        if (!StringUtils.isEmpty(projectDto.getEndDate())) {
            try {
                LocalDate endDate = LocalDate.parse(projectDto.getEndDate());
                if (startDate.isAfter(endDate)) {
                    throw new StartDateAfterEndDateException(endDate, startDate);
                }
            } catch (DateTimeParseException e) {
                throw new InvalidProjectInfoException("End Date is not valid!");
            }
        }
    }
}
