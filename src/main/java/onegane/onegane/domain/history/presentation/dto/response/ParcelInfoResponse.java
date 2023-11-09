package onegane.onegane.domain.history.presentation.dto.response;

import lombok.Getter;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.ParcelSize;
import onegane.onegane.domain.history.domain.State;

@Getter
public class ParcelInfoResponse {

    private final State state;
    private final Integer x;
    private final Integer z;

    public ParcelInfoResponse(History history, ParcelSize parcelSize) {
        this.state = history.getState();
        this.x = parcelSize.getX();
        this.z = parcelSize.getZ();
    }
}
