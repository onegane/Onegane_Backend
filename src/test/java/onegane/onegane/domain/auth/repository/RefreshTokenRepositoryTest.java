package onegane.onegane.domain.auth.repository;

import onegane.onegane.domain.auth.domain.RefreshToken;
import onegane.onegane.global.jwt.util.JwtProperties;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RefreshTokenRepositoryTest {

    @Autowired
    private RefreshTokenRepository repository;

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    @DisplayName("redis create part")
    public void create() {
        // given
        String email = "asdf@gmail.com";
        String accessToken = jwtProvider.createAccessToken(email, JwtProperties.ACCESS_TOKEN_EXPIRED);
        String refreshToken = jwtProvider.createRefreshToken(email, JwtProperties.REFRESH_TOKEN_EXPIRED);
        RefreshToken refreshTokenObject = new RefreshToken(email, accessToken, refreshToken);

        // when
        repository.save(refreshTokenObject);

        // then
        RefreshToken result = repository.findByAccessToken(accessToken).get();
        Assertions.assertThat(result.getAccessToken().equals(accessToken));
        Assertions.assertThat(result.getRefreshToken().equals(refreshToken));
    }

    @AfterEach
    public void delete() {
        repository.deleteAll();
    }
}