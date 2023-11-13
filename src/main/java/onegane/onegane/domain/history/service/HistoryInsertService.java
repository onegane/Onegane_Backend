package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.State;
import onegane.onegane.domain.history.presentation.dto.request.NewHistoryRequestDto;
import onegane.onegane.domain.history.presentation.dto.response.SaveHistoryResponseDto;
import onegane.onegane.domain.history.repository.HistoryRepository;
import onegane.onegane.domain.user.service.GetUserOneService;
import onegane.onegane.global.exception.domain.ApiErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryInsertService {

    private final HistoryRepository historyRepository;
    private final GetUserOneService getUserOneService;

    public ResponseEntity<?> execute(NewHistoryRequestDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (historyRepository.existsByTrackingNumber(dto.getTrackingNumber())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(
                            ApiErrorResult.builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .summary("AlreadyExistTrackingNumber")
                                    .message("이미 존재하는 송장번호 입니다.")
                                    .build()
                    );
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        new SaveHistoryResponseDto(
                                historyRepository.save(
                                        dto.toEntity(getUserOneService.execute(email),
                                                State.NOT_ARRIVED)
                                )
                        )
                );
    }
}
