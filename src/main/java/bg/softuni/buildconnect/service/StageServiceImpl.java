package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.dto.StageDTO;
import bg.softuni.buildconnect.entity.Project;
import bg.softuni.buildconnect.entity.Stage;
import bg.softuni.buildconnect.repository.ProjectRepository;
import bg.softuni.buildconnect.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StageServiceImpl implements StageService {

    private final StageRepository stageRepository;
    private final ProjectRepository projectRepository;

    public StageServiceImpl(StageRepository stageRepository, ProjectRepository projectRepository) {
        this.stageRepository = stageRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public StageDTO create(StageDTO dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Stage stage = new Stage();
        stage.setName(dto.getName());
        stage.setStatus(dto.getStatus());
        stage.setStartDate(dto.getStartDate());
        stage.setEndDate(dto.getEndDate());
        stage.setProject(project);

        return toDTO(stageRepository.save(stage));
    }

    @Override
    public List<StageDTO> getStagesByProjectId(Long projectId) {
        return stageRepository.findByProjectId(projectId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private StageDTO toDTO(Stage stage) {
        StageDTO dto = new StageDTO();
        dto.setId(stage.getId());
        dto.setName(stage.getName());
        dto.setStatus(stage.getStatus());
        dto.setStartDate(stage.getStartDate());
        dto.setEndDate(stage.getEndDate());
        dto.setProjectId(stage.getProject().getId());
        return dto;
    }
}
