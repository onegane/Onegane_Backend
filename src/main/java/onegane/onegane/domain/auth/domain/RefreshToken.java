package onegane.onegane.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@Getter
@NoArgsConstructor
@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 14)
public class RefreshToken {

    @Id
    private String id; // 사용자 이메일

    @Indexed
    private String accessToken;

    private String refreshToken;

    @Builder
    public RefreshToken(String id, String accessToken, String refreshToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
