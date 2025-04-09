package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.entity.Project;
import bg.softuni.buildconnect.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    @Override
    public List<Project> getByCreator(String username) {
        return projectRepository.findByCreatedByUsername(username);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
