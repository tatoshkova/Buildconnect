package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.dto.ImageDTO;
import bg.softuni.buildconnect.entity.Image;
import bg.softuni.buildconnect.entity.Project;
import bg.softuni.buildconnect.repository.ImageRepository;
import bg.softuni.buildconnect.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ProjectRepository projectRepository;

    public ImageServiceImpl(ImageRepository imageRepository, ProjectRepository projectRepository) {
        this.imageRepository = imageRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public ImageDTO create(ImageDTO dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Image image = new Image();
        image.setUrl(dto.getUrl());
        image.setProject(project);

        return toDTO(imageRepository.save(image));
    }

    @Override
    public List<ImageDTO> getByProjectId(Long projectId) {
        return imageRepository.findByProjectId(projectId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ImageDTO toDTO(Image image) {
        ImageDTO dto = new ImageDTO();
        dto.setId(image.getId());
        dto.setUrl(image.getUrl());
        dto.setProjectId(image.getProject().getId());
        return dto;
    }
}
