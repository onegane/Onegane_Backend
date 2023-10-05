package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.presentation.dto.request.NewHistoryRequestDto;
import onegane.onegane.domain.history.repository.HistoryRepository;
import onegane.onegane.global.exception.domain.ApiErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryUpdateService {

    private final HistoryRepository historyRepository;

    public ResponseEntity<?> update(Long id, NewHistoryRequestDto dto) {
        Optional<History> getHistory = historyRepository.findById(id);

        if (getHistory.isPresent()) {
            History history = getHistory.get();

            return ResponseEntity.ok(
                    historyRepository.save(history.updateInfo(dto.getTrackingNumber(), dto.getParcelNickname())).getId()
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiErrorResult.builder()
                                .status("HistoryNotFound")
                                .message("택배 내역이 존재하지 않습니다.")
                                .build()
                );
    }

    public ResponseEntity<?> update(History history) {
        return ResponseEntity.ok(
                historyRepository.save(history).getId()
        );
    }
}
