package onegane.onegane.domain.auth.controller;

import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.auth.service.AuthSignupOrSigninService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthSignupOrSigninService authSignupOrSigninService;

    @PostMapping("/bsm")
    public ResponseEntity loginRequest(@RequestParam(name = "code") String authCode) throws BsmOAuthInvalidClientException, IOException, BsmOAuthCodeNotFoundException, BsmOAuthTokenNotFoundException {
        return ResponseEntity.ok(authSignupOrSigninService.login(authCode));
    }
}
