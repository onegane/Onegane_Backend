package onegane.onegane.domain.trackingNumber.presentation.controller;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.trackingNumber.presentation.dto.TrackingNumberRequestDto;
import onegane.onegane.domain.trackingNumber.service.TrackingNumberInsertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/number")
@RequiredArgsConstructor
public class TrackingNumberController {

    private final TrackingNumberInsertService trackingNumberInsertService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity insertTrackingNumber(HttpServletRequest request,
                                               @RequestBody TrackingNumberRequestDto dto) {
        System.out.println(dto.getTrackingNumber());
        System.out.println(dto.getNickname());
        return trackingNumberInsertService.insert(request, dto);
    }
}
