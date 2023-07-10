package onegane.onegane.domain.trackingNumber.repository;

import onegane.onegane.domain.trackingNumber.domain.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Long> {

    Optional<TrackingNumber> deleteByUserId(Long id);
}
