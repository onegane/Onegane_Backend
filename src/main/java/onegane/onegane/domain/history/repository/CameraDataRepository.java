package onegane.onegane.domain.history.repository;

import onegane.onegane.domain.history.domain.CameraData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraDataRepository extends JpaRepository<CameraData, String> {
}
