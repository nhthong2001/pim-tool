package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.ProjectStatus;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.dto.SearchDataDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.*;
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
        return projectService.findAllProjectInfo()
                .stream()
                .map(project -> mapper.projectToProjectDto(project))
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<ProjectDto> search(@RequestParam String keyword, @RequestParam ProjectStatus status) {
        SearchDataDto searchDataDto = new SearchDataDto(keyword, status);
        return projectService.searchProject(searchDataDto)
                .stream()
                .map(project -> mapper.projectToProjectDto(project))
                .collect(Collectors.toList());
    }

    @GetMapping("/searchByKeyword")
    public List<ProjectDto> searchByKeyword(@RequestParam String keyword) {
        return projectService.searchProject(keyword)
                .stream()
                .map(project -> {
                    List<String> members = projectService.getListEmployee(project.getId());
                    return mapper.projectToFullInfoProjectDto(project, members);
                })
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ProjectDto getById(@PathVariable("id") Long id) throws ProjectNotFoundException {
        Optional<Project> project = projectService.findById(id);
        List<String> members = projectService.getListEmployee(project.get().getId());

        return mapper.projectToFullInfoProjectDto(project.get(), members);
    }


    @PutMapping
    public ProjectDto update(@Valid @RequestBody ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectMemberException, InvalidGroupException, ProjectNotFoundException, InvalidProjectNumberException {
        Project projectUpdated = projectService.updateProject(projectDto);

        return mapper.projectToProjectDto(projectUpdated);
    }

    @PostMapping
    public ProjectDto addNew(@Valid @RequestBody ProjectDto projectDto) throws StartDateAfterEndDateException, InvalidProjectMemberException, ProjectNotFoundException, InvalidGroupException, InvalidProjectNumberException {
        Project projectNew = projectService.saveProject(projectDto);

        return mapper.projectToProjectDto(projectNew);
    }

    @GetMapping("/isValidProjectNumber/{projectNumber}")
    public Boolean isValidProjectNumber(@PathVariable("projectNumber") Integer projectNumber) {
        Boolean isValid = projectService.isValidProjectNumber(projectNumber);
        return isValid;
    }

    @DeleteMapping("{id}")
    public Long deleteProject(@PathVariable Long id) throws DeleteProjectException, ProjectNotFoundException {
        return projectService.deleteProject(id);
    }

    @DeleteMapping("/deleteListProject")
    public List<Long> deleteListProject(@RequestBody List<Long> listId) {
        return projectService.deleteListProject(listId);
    }

}
