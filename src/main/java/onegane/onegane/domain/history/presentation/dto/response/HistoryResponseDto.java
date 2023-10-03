package onegane.onegane.domain.history.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.State;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HistoryResponseDto {

    private Long id;
    private Long trackingNumber;
    private String parcelNickname;
    private State state;
    private LocalDateTime createdDate;
    private LocalDateTime getDate;

    @Builder
    public HistoryResponseDto(Long id, Long trackingNumber, String parcelNickname, State state, LocalDateTime createdDate, LocalDateTime getDate) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.parcelNickname = parcelNickname;
        this.state = state;
        this.createdDate = createdDate;
        this.getDate = getDate;
    }

    public HistoryResponseDto(History history) {
        id = history.getId();
        trackingNumber = history.getTrackingNumber();
        parcelNickname = history.getParcelNickname();
        state = history.getState();
        createdDate = history.getCreatedTime();
        getDate = history.getLastModifiedTime();
    }
}
