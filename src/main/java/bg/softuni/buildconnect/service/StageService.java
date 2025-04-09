package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.dto.StageDTO;

import java.util.List;

public interface StageService {
    StageDTO create(StageDTO dto);
    List<StageDTO> getStagesByProjectId(Long projectId);
}
