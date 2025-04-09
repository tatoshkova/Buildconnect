package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.dto.DocumentDTO;
import bg.softuni.buildconnect.entity.Document;
import bg.softuni.buildconnect.entity.Project;
import bg.softuni.buildconnect.repository.DocumentRepository;
import bg.softuni.buildconnect.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ProjectRepository projectRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository, ProjectRepository projectRepository) {
        this.documentRepository = documentRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public DocumentDTO upload(DocumentDTO dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Document doc = new Document();
        doc.setName(dto.getName());
        doc.setFileUrl(dto.getFileUrl());
        doc.setProject(project);

        return toDTO(documentRepository.save(doc));
    }

    @Override
    public List<DocumentDTO> getByProjectId(Long projectId) {
        return documentRepository.findByProjectId(projectId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private DocumentDTO toDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setName(document.getName());
        dto.setFileUrl(document.getFileUrl());
        dto.setProjectId(document.getProject().getId());
        return dto;
    }
}
