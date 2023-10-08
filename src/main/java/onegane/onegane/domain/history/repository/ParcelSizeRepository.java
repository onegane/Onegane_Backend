package onegane.onegane.domain.history.repository;

import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.ParcelSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelSizeRepository extends JpaRepository<ParcelSize, Long> {

    ParcelSize findByHistory(History history);
}
