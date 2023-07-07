package onegane.onegane.domain.auth.service;

import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.resource.BsmUserResource;
import leehj050211.bsmOauth.exception.BsmOAuthCodeNotFoundException;
import leehj050211.bsmOauth.exception.BsmOAuthInvalidClientException;
import leehj050211.bsmOauth.exception.BsmOAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.user.domain.Role;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.domain.user.presentation.dto.UserResponseDto;
import onegane.onegane.domain.user.repository.UserRepository;
import onegane.onegane.global.jwt.dto.TokenResponseDto;
import onegane.onegane.global.jwt.util.JwtProperties;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthSignupOrSigninService {

    @Value("${bsm.client-id}")
    private String bsmClientId;

    @Value("${bsm.client-secret}")
    private String bsmClientSecret;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenResponseDto login(String authCode) throws IOException, BsmOAuthTokenNotFoundException, BsmOAuthCodeNotFoundException, BsmOAuthInvalidClientException {
        BsmOauth bsmOauth = new BsmOauth(bsmClientId, bsmClientSecret);
        String token = bsmOauth.getToken(authCode);
        BsmUserResource resource = bsmOauth.getResource(token);
        return TokenResponseDto.builder()
                .accessToken(jwtProvider.createAccessToken(resource.getEmail(), JwtProperties.ACCESS_TOKEN_EXPIRED))
                .refreshToken(jwtProvider.createRefreshToken(resource.getEmail(), JwtProperties.REFRESH_TOKEN_EXPIRED))
                .userResponseDto(updateOrSave(resource))
                .build();
    }

    @Transactional
    public UserResponseDto updateOrSave(BsmUserResource resource) {
        Optional<User> user = userRepository.findByEmail(resource.getEmail());

        if (user.isEmpty()) {
            user = Optional.ofNullable(save(resource));
        } else {
            user.get().update(resource);
        }

        return UserResponseDto.builder()
                .grade(user.get().getGrade())
                .classNo(user.get().getClassNo())
                .email(user.get().getEmail())
                .name(user.get().getName())
                .build();
    }

    @Transactional
    public User save(BsmUserResource resource) {
        return userRepository.save(
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
