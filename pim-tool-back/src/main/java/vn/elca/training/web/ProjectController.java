package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.service.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gtn
 *
 */
@RestController
@RequestMapping("/projects")
public class ProjectController extends AbstractApplicationController {

    @Autowired
    @Qualifier("projectServiceImpl")
    private ProjectService projectService;

    @GetMapping
    public List<ProjectDto> getAll() {
        return projectService.findAll()
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/search")
    public List<ProjectDto> search(@RequestBody String keyword) {
        return projectService.findByKeyword(keyword)
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProjectDto getById(@PathVariable("id") Long id) {
        return mapper.projectToProjectDto(projectService.findById(id).get());
    }

    @PostMapping
    public String addNew(@RequestBody ProjectDto projectDto) {

        projectService.update(projectDto);


        return "Successful to update Project";
    }

}
