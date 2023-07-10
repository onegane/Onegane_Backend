package onegane.onegane.domain.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 14)
public class RefreshToken {

    @Id
    private String id; // 사용자 이메일

    @Indexed
    private String accessToken;

    private String refreshToken;

    public RefreshToken update(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        return this;
    }
}
