package bg.softuni.buildconnect.controller;

import bg.softuni.buildconnect.dto.DocumentDTO;
import bg.softuni.buildconnect.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public DocumentDTO uploadDocument(@RequestBody DocumentDTO dto) {
        return documentService.upload(dto);
    }

    @GetMapping("/project/{projectId}")
    public List<DocumentDTO> getDocumentsByProject(@PathVariable Long projectId) {
        return documentService.getByProjectId(projectId);
    }
}
