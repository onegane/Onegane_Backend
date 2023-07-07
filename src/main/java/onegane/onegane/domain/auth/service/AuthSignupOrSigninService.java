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

    @Transactional
    public User login(String authCode) throws IOException, BsmOAuthTokenNotFoundException, BsmOAuthCodeNotFoundException, BsmOAuthInvalidClientException {
        BsmOauth bsmOauth = new BsmOauth(bsmClientId, bsmClientSecret);
        String token = bsmOauth.getToken(authCode);
        BsmUserResource resource = bsmOauth.getResource(token);
        return updateOrSave(resource);
    }

    @Transactional
    public User updateOrSave(BsmUserResource resource) {
        Optional<User> user = userRepository.findByStudentNumber(resource.getUserCode());

        if (user.isEmpty()) {
            return save(resource);
        }

        return user.get().update(resource);
    }

    @Transactional
    public User save(BsmUserResource resource) {
        return userRepository.save(
                User.builder()
                    .studentNumber(resource.getUserCode())
                    .name(resource.getNickname())
                    .entranceYear(resource.getStudent().getEnrolledAt())
                    .email(resource.getEmail())
                    .role(Role.ROLE_USER)
                    .build()
        );
    }
}
