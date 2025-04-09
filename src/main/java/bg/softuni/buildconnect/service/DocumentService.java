package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.dto.DocumentDTO;

import java.util.List;

public interface DocumentService {
    DocumentDTO upload(DocumentDTO dto);
    List<DocumentDTO> getByProjectId(Long projectId);
}
