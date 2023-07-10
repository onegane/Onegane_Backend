package onegane.onegane.domain.history.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.State;
import onegane.onegane.domain.user.domain.User;

@Getter
@NoArgsConstructor
public class HistoryRequestDto {

    private Long trackingNumber;
    private String parcelNickname;

    @Builder
    public HistoryRequestDto(Long trackingNumber, String parcelNickname) {
        this.trackingNumber = trackingNumber;
        this.parcelNickname = parcelNickname;
    }

    public History toEntity(User user, State state) {
        return History.builder()
                .trackingNumber(trackingNumber)
                .parcelNickname(parcelNickname)
                .user(user)
                .state(state)
                .build();
    }
}
