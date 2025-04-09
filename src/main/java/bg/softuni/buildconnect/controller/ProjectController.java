package bg.softuni.buildconnect.controller;

import bg.softuni.buildconnect.dto.ProjectDTO;
import bg.softuni.buildconnect.entity.Project;
import bg.softuni.buildconnect.entity.User;
import bg.softuni.buildconnect.mapper.ProjectMapper;
import bg.softuni.buildconnect.service.ProjectService;
import bg.softuni.buildconnect.repository.UserRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserRepository userRepository;

    public ProjectController(ProjectService projectService, UserRepository userRepository) {
        this.projectService = projectService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        Project project = projectService.getById(id);
        return ProjectMapper.toDTO(project);
    }

    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectDTO dto,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        User creator = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = ProjectMapper.toEntity(dto, creator);
        Project savedProject = projectService.create(project);

        return ProjectMapper.toDTO(savedProject);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.delete(id);
    }
}
