package onegane.onegane.domain.auth.service;

import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.user.domain.Role;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.domain.user.repository.UserRepository;
import onegane.onegane.global.jwt.dto.TokenResponseDto;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthSignupOrSigninService {

    @Value("${bsm.client-id}")
    private String bsmClientId;

    @Value("${bsm.client-secret}")
    private String bsmClientSecret;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public TokenResponseDto login(String authCode) throws IOException, BsmOAuthTokenNotFoundException, BsmOAuthCodeNotFoundException, BsmOAuthInvalidClientException {
        BsmOauth bsmOauth = new BsmOauth(bsmClientId, bsmClientSecret);
        String token = bsmOauth.getToken(authCode);
        BsmUserResource resource = bsmOauth.getResource(token);

        String accessToken = jwtProvider.createAccessToken(resource.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(resource.getEmail());
        refreshTokenService.saveRefreshToken(resource.getEmail(), accessToken, refreshToken);
        updateOrSave(resource);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void updateOrSave(BsmUserResource resource) {
        Optional<User> user = userRepository.findByEmail(resource.getEmail());

        if (user.isEmpty()) {
            save(resource);
        } else {
            userRepository.save(user.get().update(resource));
        }
    }

    public void save(BsmUserResource resource) {
        userRepository.save(
                User.builder()
                    .grade(resource.getStudent().getGrade())
                    .classNo(resource.getStudent().getClassNo())
                    .name(resource.getNickname())
                    .entranceYear(resource.getStudent().getEnrolledAt())
                    .email(resource.getEmail())
                    .role(Role.ROLE_USER)
                    .build()
        );
    }
}
