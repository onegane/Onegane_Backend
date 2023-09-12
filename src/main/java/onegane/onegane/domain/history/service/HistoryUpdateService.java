package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.presentation.dto.NewHistoryRequestDto;
import onegane.onegane.domain.history.repository.HistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoryUpdateService {

    private final HistoryRepository historyRepository;

    @Transactional
    public ResponseEntity update(Long id, NewHistoryRequestDto dto) {
        History history = historyRepository.findById(id).get();
        historyRepository.save(history.update(dto.getTrackingNumber(), dto.getParcelNickname()));
        return ResponseEntity.ok("success");
    }
}
