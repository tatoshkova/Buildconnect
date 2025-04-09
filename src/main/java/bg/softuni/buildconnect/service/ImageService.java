package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.dto.ImageDTO;

import java.util.List;

public interface ImageService {
    ImageDTO create(ImageDTO dto);
    List<ImageDTO> getByProjectId(Long projectId);
}
