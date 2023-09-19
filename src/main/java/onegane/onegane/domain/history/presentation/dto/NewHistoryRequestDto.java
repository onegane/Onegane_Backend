package onegane.onegane.domain.history.presentation.dto;

import lombok.Getter;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.State;
import onegane.onegane.domain.user.domain.User;

@Getter
public class NewHistoryRequestDto {

    private Long trackingNumber;
    private String parcelNickname;

    public History toEntity(User user, State state) {
        return History.builder()
                .trackingNumber(trackingNumber)
                .parcelNickname(parcelNickname)
                .user(user)
                .state(state)
                .build();
    }
}
