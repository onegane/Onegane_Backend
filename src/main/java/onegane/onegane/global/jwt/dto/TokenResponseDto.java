package onegane.onegane.global.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onegane.onegane.domain.user.presentation.dto.UserResponseDto;

@Getter
@NoArgsConstructor
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private UserResponseDto userResponseDto;

    @Builder
    public TokenResponseDto(String accessToken, String refreshToken, UserResponseDto userResponseDto) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userResponseDto = userResponseDto;
    }
}
