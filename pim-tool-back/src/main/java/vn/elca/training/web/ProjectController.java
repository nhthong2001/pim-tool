package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ProjectNotFoundException;
import vn.elca.training.service.ProjectService;

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
    @Autowired
    @Qualifier("firstDummyProjectServiceImpl")
    private ProjectService firstDummyProjectService;

    @Autowired
    @Qualifier("secondDummyProjectServiceImpl")
    private ProjectService secondDummyProjectService;

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

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Error projectNotFound(ProjectNotFoundException e) {
        long projectId = e.getProjectId();

        return new Error("Project [" + projectId + "] not found");
    }

    @GetMapping("/{id}")
    public ProjectDto getById(@PathVariable("id") Long id) {
        Optional<Project> project = firstDummyProjectService.findById(id);

        if (project.isEmpty()) {
            throw new ProjectNotFoundException(id);
        }

        return mapper.projectToProjectDto(project.get());
    }


    @PostMapping
    public String addNew(@RequestBody ProjectDto projectDto) {

        Project project = secondDummyProjectService.update(projectDto);
        if (project == null) {
            throw new ProjectNotFoundException(projectDto.getId());
        }


        return "Successful to update Project [" + projectDto.getId() + "]";
    }

}
