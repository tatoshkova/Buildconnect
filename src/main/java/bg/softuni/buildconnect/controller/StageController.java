package bg.softuni.buildconnect.controller;

import bg.softuni.buildconnect.dto.StageDTO;
import bg.softuni.buildconnect.service.StageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    private final StageService stageService;

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping
    public StageDTO createStage(@RequestBody StageDTO dto) {
        return stageService.create(dto);
    }

    @GetMapping("/project/{projectId}")
    public List<StageDTO> getStagesByProject(@PathVariable Long projectId) {
        return stageService.getStagesByProjectId(projectId);
    }
}
