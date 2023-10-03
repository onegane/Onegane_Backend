package onegane.onegane.domain.auth.controller;

import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.auth.service.AuthLogoutService;
import onegane.onegane.domain.auth.service.AuthSignupOrSigninService;
import onegane.onegane.domain.auth.service.GetUserService;
import onegane.onegane.domain.auth.service.RefreshTokenService;
import onegane.onegane.global.jwt.dto.TokenResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final GetUserService getUserService;
    private final AuthSignupOrSigninService authSignupOrSigninService;
    private final AuthLogoutService authLogoutService;
    private final RefreshTokenService refreshTokenService;

    @GetMapping
    public ResponseEntity<?> getUserData(HttpServletRequest request) {
        return getUserService.execute(request);
    }

    @PostMapping("/bsm")
    public ResponseEntity<TokenResponseDto> login(@RequestParam(name = "code") String authCode) throws BsmOAuthInvalidClientException, IOException, BsmOAuthCodeNotFoundException, BsmOAuthTokenNotFoundException {
        return ResponseEntity.ok(authSignupOrSigninService.login(authCode));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        return ResponseEntity.ok(authLogoutService.logout(request));
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> updateAccessToken(HttpServletRequest request) {
        return refreshTokenService.updateAccessToken(request);
    }
}
