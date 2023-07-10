package onegane.onegane.domain.trackingNumber.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onegane.onegane.domain.trackingNumber.domain.TrackingNumber;
import onegane.onegane.domain.user.domain.User;

@Getter
@NoArgsConstructor
public class TrackingNumberRequestDto {

    private Long trackingNumber;
    private String nickname;

    @Builder
    public TrackingNumberRequestDto(Long trackingNumber, String nickname) {
        this.trackingNumber = trackingNumber;
        this.nickname = nickname;
    }

    public TrackingNumber toEntity(User user) {
        return TrackingNumber.builder()
                .trackingNumber(this.trackingNumber)
                .nickname(this.nickname)
                .user(user)
                .build();
    }
}
