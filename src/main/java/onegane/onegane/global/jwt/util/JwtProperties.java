package onegane.onegane.global.jwt.util;

public class JwtProperties {

    public static final Long ACCESS_TOKEN_EXPIRED = 1000 * 10L; // 30분
    public static final Long REFRESH_TOKEN_EXPIRED = 1000* 60 * 60 * 24 * 14L; // 2주
}
