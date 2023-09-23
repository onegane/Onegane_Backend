package onegane.onegane.global.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import onegane.onegane.global.config.security.auth.AuthDetails;
import onegane.onegane.global.config.security.auth.AuthDetailsService;
import onegane.onegane.global.jwt.dto.TokenFilterResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final AuthDetailsService authDetailsService;

    public String createAccessToken(String email, String userName) {
        return createToken(email, userName, JwtProperties.ACCESS_TOKEN_EXPIRED);
    }

    public String createRefreshToken(String email, String userName) {
        return createToken(email, userName, JwtProperties.REFRESH_TOKEN_EXPIRED);
    }

    private String createToken(String email, String userName, Long time) {
        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("userName", userName);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenFilterResponse isValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey(secretKey))
                    .parseClaimsJws(token)
                    .getBody();
            return TokenFilterResponse.builder()
                    .isValid(true)
                    .message("토큰이 정상적입니다.")
                    .build();
        } catch (SignatureException e) {
            return TokenFilterResponse.builder()
                    .isValid(false)
                    .message("토큰의 시그니쳐가 올바르지 않습니다.")
                    .build();
        } catch (MalformedJwtException e) {
            return TokenFilterResponse.builder()
                    .isValid(false)
                    .message("JWT의 형식이 유효하지 않습니다.")
                    .build();
        } catch (ExpiredJwtException e) {
            return TokenFilterResponse.builder()
                    .isValid(false)
                    .message("JWT가 만료되었습니다.")
                    .build();
        } catch (UnsupportedJwtException e) {
            return TokenFilterResponse.builder()
                    .isValid(false)
                    .message("지원되지 않는 JWT입니다.")
                    .build();
        } catch (IllegalArgumentException | NullPointerException e) {
            return TokenFilterResponse.builder()
                    .isValid(false)
                    .message("토큰의 자료형이 잘못되었거나 존재하지 않습니다.")
                    .build();
        }
    }

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = (AuthDetails) authDetailsService.loadUserByUsername(extractEmail(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey(secretKey))
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }
}
