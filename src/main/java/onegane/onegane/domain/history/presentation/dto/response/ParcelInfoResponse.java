package onegane.onegane.domain.history.presentation.dto.response;

import lombok.Getter;
import onegane.onegane.domain.history.domain.CaseSize;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.ParcelSize;
import onegane.onegane.domain.history.domain.State;

@Getter
public class ParcelInfoResponse {

    private final State state;
    private final Integer x;
    private final Integer z;
    private final Integer caseX;
    private final Integer caseZ;

    public ParcelInfoResponse(History history, ParcelSize parcelSize, CaseSize caseSize) {
        this.state = history.getState();
        this.x = parcelSize.getX();
        this.z = parcelSize.getZ();

        if (caseSize == null) {
            this.caseX = 120;
            this.caseZ = 120;
        } else {
            this.caseX = caseSize.getX();
            this.caseZ = caseSize.getZ();
        }
    }
}
