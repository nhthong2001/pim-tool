package vn.elca.training.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.ProjectRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplMockTest {
    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectServiceImpl projectService;

    @Test
    void findAll() {
        List<Project> mockProjects = new ArrayList<>();
        mockProjects.add(new Project("KSTA", LocalDate.now()));
        mockProjects.add(new Project("LAGAPEO", LocalDate.now()));
        mockProjects.add(new Project("ZHQUEST", LocalDate.now()));
        mockProjects.add(new Project("SECUTIX", LocalDate.now()));

        when(projectRepository.findAll()).thenReturn(mockProjects);

        List<Project> actualProjects = projectService.findAll();

        assertThat(actualProjects.size()).isEqualTo(mockProjects.size());

        verify(projectRepository).findAll();
    }

    @Test
    void findById() {

        Project mockProject = new Project("SECUTIX", LocalDate.now());
        when(projectRepository.findById(1L)).thenReturn(Optional.of(mockProject));

        Project actualProject = projectService.findById(1L).orElse(null);

        assertThat(actualProject).isEqualTo(mockProject);

        verify(projectRepository).findById(1L);
    }

    @Test
    void findByKeyword() {
        List<Project> mockProjects = new ArrayList<>();
        mockProjects.add(new Project("KSTA", LocalDate.now()));
        mockProjects.add(new Project("ZHQUEST", LocalDate.now()));
        mockProjects.add(new Project("SECUTIX", LocalDate.now()));

        when(projectRepository.findByNameContains("T")).thenReturn(mockProjects);

        List<Project> actualProjects = projectService.findByKeyword("T");

        assertThat(actualProjects).isEqualTo(mockProjects);

        verify(projectRepository).findByNameContains("T");
    }

    @Test
    void update() {


    }
}