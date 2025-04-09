package bg.softuni.buildconnect.mapper;

import bg.softuni.buildconnect.dto.ProjectDTO;
import bg.softuni.buildconnect.entity.Project;
import bg.softuni.buildconnect.entity.User;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();

        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setAddress(project.getAddress());
        dto.setStatus(project.getStatus());

        if (project.getCreatedBy() != null) {
            dto.setCreatedByUsername(project.getCreatedBy().getUsername());
        }

        return dto;
    }

    public static Project toEntity(ProjectDTO dto, User creator) {
        Project project = new Project();

        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setAddress(dto.getAddress());
        project.setStatus(dto.getStatus());
        project.setCreatedBy(creator);

        return project;
    }
}
