package onegane.onegane.domain.history.presentation.controller;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.presentation.dto.request.NewHistoryRequestDto;
import onegane.onegane.domain.history.service.HistoryDeleteService;
import onegane.onegane.domain.history.service.HistoryGetService;
import onegane.onegane.domain.history.service.HistoryInsertService;
import onegane.onegane.domain.history.service.HistoryUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/number")
@RequiredArgsConstructor
public class HistoryController{

    private final HistoryGetService historyGetService;
    private final HistoryInsertService historyInsertService;
    private final HistoryUpdateService historyUpdateService;
    private final HistoryDeleteService historyDeleteService;

    @GetMapping
    public ResponseEntity<?> getHistory(HttpServletRequest request) {
        return historyGetService.findAll(request);
    }

    @PostMapping
    public ResponseEntity<?> insertHistory(@RequestBody NewHistoryRequestDto dto) {
        return historyInsertService.execute(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHistory(@PathVariable("id") Long id,
                                        @RequestBody NewHistoryRequestDto dto) {
        return historyUpdateService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteHistory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(historyDeleteService.delete(id));
    }
}
