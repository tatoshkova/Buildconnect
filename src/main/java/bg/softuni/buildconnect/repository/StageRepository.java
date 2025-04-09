package bg.softuni.buildconnect.repository;

import bg.softuni.buildconnect.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    List<Stage> findByProjectId(Long projectId);

}
