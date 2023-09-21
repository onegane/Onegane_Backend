package onegane.onegane.domain.history.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class CameraData {

    @Id
    private String trackingNumber;

    @Builder
    public CameraData(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
