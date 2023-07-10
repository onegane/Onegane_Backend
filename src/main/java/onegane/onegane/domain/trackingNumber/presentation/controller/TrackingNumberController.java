package onegane.onegane.domain.trackingNumber.presentation.controller;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.trackingNumber.presentation.dto.TrackingNumberRequestDto;
import onegane.onegane.domain.trackingNumber.service.TrackingNumberDeleteService;
import onegane.onegane.domain.trackingNumber.service.TrackingNumberInsertService;
import onegane.onegane.domain.trackingNumber.service.TrackingNumberUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/number")
@RequiredArgsConstructor
public class TrackingNumberController {

    private final TrackingNumberInsertService trackingNumberInsertService;
    private final TrackingNumberUpdateService trackingNumberUpdateService;
    private final TrackingNumberDeleteService trackingNumberDeleteService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity insertTrackingNumber(HttpServletRequest request,
                                               @RequestBody TrackingNumberRequestDto dto) {
        return trackingNumberInsertService.insert(request, dto);
    }

    @PutMapping("/:id")
    public ResponseEntity updateTrackingNumber(@PathVariable Long id,
                                               @RequestBody TrackingNumberRequestDto dto) {
        return trackingNumberUpdateService.update(id, dto);
    }
}
