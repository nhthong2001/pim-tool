package vn.elca.training.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gtn
 */
@Component
public class ApplicationMapper {
    public ApplicationMapper() {
        // Mapper utility class
    }

    @Autowired
    private ProjectService projectService;

    public ProjectDto projectToProjectDto(Project entity, List<String> members) {

        ProjectDto dto = ProjectDto.builder()
                .id(entity.getId())
                .projectNumber(entity.getProjectNumber())
                .projectName(entity.getName())
                .customer(entity.getCustomer())
                .group(entity.getGroup().getGroupLeader().getVisa()) // handle
                .member(members) // handle
                .status(entity.getStatus().toString()) // handle
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();

        return dto;
    }


    public GroupDto groupToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setLeader_id(group.getGroupLeader().getId());
        groupDto.setLeader_visa(group.getGroupLeader().getVisa());
        return groupDto;
    }
}
