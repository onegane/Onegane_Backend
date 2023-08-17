package onegane.onegane.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthLogoutService {

    private final RefreshTokenService refreshTokenService;

    public ResponseEntity<?> logout(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").split(" ")[1];

        if (refreshTokenService.removeRefreshToken(accessToken)) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(401).body("로그인 된 유저가 아닙니다.");
        }
    }
}
