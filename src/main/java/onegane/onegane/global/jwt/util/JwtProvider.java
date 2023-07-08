package onegane.onegane.global.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import onegane.onegane.global.config.security.auth.AuthDetails;
import onegane.onegane.global.config.security.auth.AuthDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final AuthDetailsService authDetailsService;

    public String createAccessToken(String email, Long time) {
        return createToken(email, time);
    }

    public String createRefreshToken(String email, Long time) {
        return createToken(email, time);
    }

    private String createToken(String email, Long time) {
        Claims claims = Jwts.claims();
        claims.put("email", email);

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

    public boolean isValid(String token, HttpServletResponse response) throws IOException {
        String errorMessage = "Invalid JWT.";

        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey(secretKey))
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (JwtException e) {
            if (e instanceof SignatureException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Signature.");
            } else if (e instanceof MalformedJwtException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
            } else if (e instanceof ExpiredJwtException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Expired JWT.");
            } else if (e instanceof UnsupportedJwtException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unsupported JWT.");
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT claims string is empty.");
        } catch (NullPointerException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT RefreshToken is empty.");
        }

        return false;
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
