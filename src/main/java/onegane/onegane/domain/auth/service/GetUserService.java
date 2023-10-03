package onegane.onegane.domain.auth.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.domain.user.repository.UserRepository;
import onegane.onegane.global.exception.domain.ApiErrorResult;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<?> execute(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").split(" ")[1].trim();
        Optional<User> getUser = userRepository.findByEmail(jwtProvider.extractEmail(accessToken));

        if (getUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(
                            ApiErrorResult.builder()
                                    .status("UserNotFound")
                                    .message("해당 유저가 존재하지 않습니다.")
                                    .build()
                    );
        }

        return ResponseEntity.ok(getUser.get());
    }
}
