package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.State;
import onegane.onegane.domain.history.presentation.dto.HistoryRequestDto;
import onegane.onegane.domain.history.repository.HistoryRepository;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.domain.user.repository.UserRepository;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class HistoryInsertService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public ResponseEntity insert(HttpServletRequest request, HistoryRequestDto dto) {
        String accessToken = request.getHeader("Authorization").split(" ")[1].trim();
        String email = jwtProvider.extractEmail(accessToken);
        User user = userRepository.findByEmail(email).get();
        historyRepository.save(dto.toEntity(user, State.WAITING));
        return ResponseEntity.ok("success");
    }
}
