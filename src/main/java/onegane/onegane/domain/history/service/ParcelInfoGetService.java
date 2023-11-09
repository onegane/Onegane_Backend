package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.presentation.dto.response.ParcelInfoResponse;
import onegane.onegane.domain.history.repository.ParcelSizeRepository;
import onegane.onegane.global.exception.domain.ApiErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParcelInfoGetService {

    private final ParcelSizeRepository parcelSizeRepository;
    private final HistoryGetService historyGetService;
    private final CaseSizeService caseSizeService;

    public ResponseEntity<?> execute(String parcelNumber) {
        History history = historyGetService.findByTrackingNumber(parcelNumber);

        if (history == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            ApiErrorResult.builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .summary("HistoryNotFound")
                                    .message("존재하지 않는 내역입니다.")
                                    .build()
                    );
        }

        return ResponseEntity.ok(
                new ParcelInfoResponse(
                        history,
                        parcelSizeRepository.findByHistory(history),
                        caseSizeService.get(history.getState())
                )
        );
    }
}
