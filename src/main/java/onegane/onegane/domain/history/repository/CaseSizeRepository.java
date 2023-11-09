package onegane.onegane.domain.history.repository;

import onegane.onegane.domain.history.domain.CaseSize;
import onegane.onegane.domain.history.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaseSizeRepository extends JpaRepository<CaseSize, Long> {

    Optional<CaseSize> findByState(State state);
}
