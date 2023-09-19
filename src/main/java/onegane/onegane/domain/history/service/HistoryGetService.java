package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.presentation.dto.HistoryResponseDto;
import onegane.onegane.domain.history.repository.HistoryRepository;
import onegane.onegane.domain.user.repository.UserRepository;
import onegane.onegane.global.exception.domain.ApiErrorResult;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryGetService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<?> findAll(HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1].trim();

        if (userRepository.findByEmail(jwtProvider.extractEmail(token)).isPresent()) {
            Long userId = userRepository.findByEmail(jwtProvider.extractEmail(token)).get().getId();
            List<History> history = historyRepository.findAllByUserId(userId);

            return ResponseEntity.ok(
                        history.stream()
                            .map(HistoryResponseDto::new)
                            .collect(Collectors.toList())
            );
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiErrorResult.builder()
                        .status("UserNotFound")
                        .message("해당 유저가 존재하지 않습니다.")
                        .build()
                );
    }
}
