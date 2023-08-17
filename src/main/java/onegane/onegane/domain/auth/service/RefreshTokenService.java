package onegane.onegane.domain.auth.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.auth.domain.RefreshToken;
import onegane.onegane.domain.auth.repository.RefreshTokenRepository;
import onegane.onegane.domain.user.presentation.dto.UserResponseDto;
import onegane.onegane.domain.user.service.GetUserOneService;
import onegane.onegane.global.jwt.dto.TokenResponseDto;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final GetUserOneService getUserOneService;
    private final JwtProvider jwtProvider;

    @Transactional
    public void saveRefreshToken(String email, String accessToken, String refreshToken) {
        refreshTokenRepository.save(
                RefreshToken.builder()
                    .id(email)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build()
        );
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
    public ResponseEntity<?> updateAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").split(" ")[1].trim();
        String refreshToken = request.getHeader("Authorization-Refresh").split(" ")[1].trim();

        if (refreshTokenRepository.findByAccessToken(accessToken).isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("토큰이 DB에 존재하지 않습니다.");
        }

        String parsingEmail = jwtProvider.extractEmail(refreshToken);

        if (refreshTokenRepository.findById(parsingEmail).isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("토큰에서 이메일이 추출되지 않습니다.");
        }

        if (refreshTokenRepository.findById(parsingEmail).get().getRefreshToken().equals(refreshToken)) {
            String newAccessToken = jwtProvider.createAccessToken(parsingEmail);
            String newRefreshToken = jwtProvider.createRefreshToken(parsingEmail);

            RefreshToken updateRefreshToken = refreshTokenRepository
                    .findById(parsingEmail)
                    .get()
                    .update(newAccessToken, newRefreshToken);

            refreshTokenRepository.save(updateRefreshToken);

            return ResponseEntity.ok(
                    TokenResponseDto.builder()
                        .accessToken(updateRefreshToken.getAccessToken())
                        .refreshToken(updateRefreshToken.getRefreshToken())
                        .userResponseDto(new UserResponseDto(getUserOneService.execute(parsingEmail)))
                        .build()
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("토큰이 올바르지 않습니다.");
    }
}
