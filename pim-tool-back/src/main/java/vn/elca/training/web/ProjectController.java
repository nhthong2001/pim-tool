package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.NotFoundException;
import vn.elca.training.service.ProjectService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author gtn
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

    @GetMapping("/search/{keyword}")
    public List<ProjectDto> search(@PathVariable String keyword) {
        return projectService.findByKeyword(keyword)
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ProjectDto getById(@PathVariable("id") Long id) {
        Optional<Project> project = projectService.findById(id);

        if (project.isEmpty()) {
            throw new NotFoundException("Can't find project with id = " + id);
        }

        return mapper.projectToProjectDto(project.get());
    }


    @PostMapping
    public String update(@RequestBody @Valid ProjectDto projectDto) {
        Project project = projectService.update(projectDto);

        if (project == null) {
            throw new NotFoundException("Can't update project with id = " + projectDto.getId());
        }


        return "Successful to update Project [" + projectDto.getId() + "]";
    }

    @PostMapping("/maintain/{id}")
    public String maintenance(@PathVariable Long id) {
        Project project = projectService.maintain(id);



        return "Successful to update Project [" + id + "]";
    }
}
