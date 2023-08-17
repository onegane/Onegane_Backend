package onegane.onegane.domain.history.repository;

import onegane.onegane.domain.history.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByUserId(Long userId);
}
