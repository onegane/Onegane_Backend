package onegane.onegane.domain.history.presentation.controller;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.presentation.dto.TrackingNumberRequestDto;
import onegane.onegane.domain.history.service.HistoryDeleteService;
import onegane.onegane.domain.history.service.HistoryInsertService;
import onegane.onegane.domain.history.service.HistoryUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/number")
@RequiredArgsConstructor
public class HistoryController{

    private final HistoryInsertService historyInsertService;
    private final HistoryUpdateService historyUpdateService;
    private final HistoryDeleteService trackingNumberDeleteService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity insertHistory(HttpServletRequest request,
                                               @RequestBody TrackingNumberRequestDto dto) {
        return historyInsertService.insert(request, dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateHistory(@PathVariable("id") Long id,
                                               @RequestBody TrackingNumberRequestDto dto) {
        return historyUpdateService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHistory(@PathVariable("id") Long id) {
        return trackingNumberDeleteService.delete(id);
    }
}
