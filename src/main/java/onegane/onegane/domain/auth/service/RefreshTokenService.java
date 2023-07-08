package onegane.onegane.domain.auth.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.auth.domain.RefreshToken;
import onegane.onegane.domain.auth.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

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
}
