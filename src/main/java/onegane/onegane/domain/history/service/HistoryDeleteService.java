package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.repository.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoryDeleteService {

    private final HistoryRepository historyRepository;

    @Transactional
    public Long delete(Long id) {
        historyRepository.deleteById(id);
        return id;
    }
}
