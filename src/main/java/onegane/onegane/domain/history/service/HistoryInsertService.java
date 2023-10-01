package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.State;
import onegane.onegane.domain.history.presentation.dto.NewHistoryRequestDto;
import onegane.onegane.domain.history.presentation.dto.SaveHistoryResponseDto;
import onegane.onegane.domain.history.repository.HistoryRepository;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.domain.user.repository.UserRepository;
import onegane.onegane.global.exception.domain.ApiErrorResult;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryInsertService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ResponseEntity<?> execute(HttpServletRequest request, NewHistoryRequestDto dto) {
        String accessToken = request.getHeader("Authorization").split(" ")[1].trim();
        String email = jwtProvider.extractEmail(accessToken);
        Optional<User> getUser = userRepository.findByEmail(email);

        if (getUser.isPresent()) {
            return ResponseEntity.ok(
                new SaveHistoryResponseDto(historyRepository.save(dto.toEntity(getUser.get(), State.NOT_ARRIVED)))
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

    public ResponseEntity<?> execute(History history) {
        return ResponseEntity.ok(
                historyRepository.save(history).getId()
        );
    }
}
