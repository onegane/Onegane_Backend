package onegane.onegane.domain.history.presentation.dto;

import lombok.Getter;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.State;

@Getter
public class SaveHistoryResponseDto {

    private final Long id;
    private final Long trackingNumber;
    private final String parcelNickname;
    private final State state;
    private final Long userId;

    public SaveHistoryResponseDto(History history) {
        this.id = history.getId();
        this.trackingNumber = history.getTrackingNumber();
        this.parcelNickname = history.getParcelNickname();
        this.state = history.getState();
        this.userId = history.getUser().getId();
    }
}
