package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.domain.History;
import onegane.onegane.domain.history.domain.State;
import onegane.onegane.domain.history.presentation.dto.request.CameraDataRequest;
import onegane.onegane.global.exception.domain.ApiErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CameraDataService {

    private final HistoryGetService historyGetService;
    private final HistoryUpdateService historyUpdateService;

    public ResponseEntity<?> execute(CameraDataRequest request) {
        String trackingNumber = request.getCode();
        History history = historyGetService.findByTrackingNumber(trackingNumber);

        if (history == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            ApiErrorResult.builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .summary("HistoryNotFound")
                                    .message("인식된 송장번호와 일치하는 내역이 없습니다.")
                                    .build()
                    );
        }

        Integer userGrade = history.getUser().getGrade();
        Integer userClassNo = history.getUser().getClassNo();
        State state = State.valueOf("STATE_" + userGrade + userClassNo);

        return ResponseEntity.ok(
                historyUpdateService.update(history.updateState(state))
        );
    }
}
