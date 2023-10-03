package onegane.onegane.global.config.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private String host;
    private Integer port;
    private String password;
}
