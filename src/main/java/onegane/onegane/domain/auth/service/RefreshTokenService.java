package onegane.onegane.domain.auth.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.auth.domain.RefreshToken;
import onegane.onegane.domain.auth.repository.RefreshTokenRepository;
import onegane.onegane.domain.user.presentation.dto.UserResponseDto;
import onegane.onegane.global.jwt.dto.TokenResponseDto;
import onegane.onegane.global.jwt.exception.InvalidRefreshTokenInfoException;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void saveRefreshToken(String email, String accessToken, String refreshToken) {
        refreshTokenRepository.save(new RefreshToken(email, accessToken, refreshToken));
    }

    @Transactional
    public Boolean removeRefreshToken(String accessToken) {
        Optional<RefreshToken> token = refreshTokenRepository.findByAccessToken(accessToken);

        if (token.isEmpty()) {
            return false;
        } else {
            refreshTokenRepository.delete(token.get());
            return true;
        }
    }

    @Transactional
    public TokenResponseDto updateAccessToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization-Refresh").split(" ")[1].trim();
        String parsingEmail = jwtProvider.extractEmail(refreshToken);

        if (refreshTokenRepository.findById(parsingEmail).get().getRefreshToken().equals(refreshToken)) {
            String newAccessToken = jwtProvider.createAccessToken(parsingEmail);
            String newRefreshToken = jwtProvider.createRefreshToken(parsingEmail);

            RefreshToken updateRefreshToken = refreshTokenRepository
                    .findById(parsingEmail)
                    .get()
                    .update(newAccessToken, newRefreshToken);

            return TokenResponseDto.builder()
                    .accessToken(updateRefreshToken.getAccessToken())
                    .refreshToken(updateRefreshToken.getRefreshToken())
                    .userResponseDto(new UserResponseDto())
                    .build();
        }

        throw new InvalidRefreshTokenInfoException("Refresh Token의 정보가 잘못되었습니다.");
    }
}
