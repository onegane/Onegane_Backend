package onegane.onegane.domain.history.presentation.controller;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.presentation.dto.HistoryRequestDto;
import onegane.onegane.domain.history.presentation.dto.HistoryResponseDto;
import onegane.onegane.domain.history.service.HistoryDeleteService;
import onegane.onegane.domain.history.service.HistoryGetService;
import onegane.onegane.domain.history.service.HistoryInsertService;
import onegane.onegane.domain.history.service.HistoryUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/number")
@RequiredArgsConstructor
public class HistoryController{

    private final HistoryGetService historyGetService;
    private final HistoryInsertService historyInsertService;
    private final HistoryUpdateService historyUpdateService;
    private final HistoryDeleteService historyDeleteService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<HistoryResponseDto>> getHistory(HttpServletRequest request) {
        return ResponseEntity.ok(historyGetService.findAll(request));
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity insertHistory(HttpServletRequest request,
                                               @RequestBody HistoryRequestDto dto) {
        return historyInsertService.insert(request, dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateHistory(@PathVariable("id") Long id,
                                               @RequestBody HistoryRequestDto dto) {
        return historyUpdateService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHistory(@PathVariable("id") Long id) {
        return historyDeleteService.delete(id);
    }
}
