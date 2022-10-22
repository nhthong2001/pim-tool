package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.entity.Group;
import vn.elca.training.service.GroupService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class GroupController extends AbstractApplicationController{
    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<GroupDto> getAll(){
        List<Group> groups = groupService.findAll();
        return groups
                .stream()
                .map(mapper::groupToGroupDto)
                .collect(Collectors.toList());
    }
}
