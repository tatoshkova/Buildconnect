package bg.softuni.buildconnect.repository;

import bg.softuni.buildconnect.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByCreatedByUsername(String username);

    List<Project> findAllByStatus(String status);

}
