package bg.softuni.buildconnect.repository;

import bg.softuni.buildconnect.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByProjectId(Long projectId);

}
