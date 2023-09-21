package onegane.onegane.domain.history.presentation.dto;

import lombok.Getter;
import onegane.onegane.domain.history.domain.CameraData;

@Getter
public class CameraDataRequest {

    private String code;

    public CameraData toEntity() {
        return CameraData.builder()
                .trackingNumber(this.code)
                .build();
    }
}
