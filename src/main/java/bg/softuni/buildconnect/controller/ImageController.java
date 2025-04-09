package bg.softuni.buildconnect.controller;

import bg.softuni.buildconnect.dto.ImageDTO;
import bg.softuni.buildconnect.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ImageDTO uploadImage(@RequestBody ImageDTO dto) {
        return imageService.create(dto);
    }

    @GetMapping("/project/{projectId}")
    public List<ImageDTO> getImagesByProject(@PathVariable Long projectId) {
        return imageService.getByProjectId(projectId);
    }
}
