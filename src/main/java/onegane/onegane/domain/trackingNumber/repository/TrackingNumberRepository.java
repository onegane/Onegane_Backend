package onegane.onegane.domain.trackingNumber.repository;

import onegane.onegane.domain.trackingNumber.domain.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Long> {
}
