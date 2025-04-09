package bg.softuni.buildconnect.service;

import bg.softuni.buildconnect.entity.Project;

import java.util.List;

public interface ProjectService {
    Project create(Project project);

    List<Project> getAll();

    Project getById(Long id);

    List<Project> getByCreator(String username);

    void delete(Long id);
}
