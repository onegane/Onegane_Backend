package onegane.onegane.domain.history.presentation.controller;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.presentation.dto.CameraDataRequest;
import onegane.onegane.domain.history.service.CameraDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/camera")
public class CameraDataController {

    private final CameraDataService cameraDataService;

    @PostMapping
    public ResponseEntity<?> saveTrackingNumber(@RequestBody CameraDataRequest request) {
        return ResponseEntity.ok(cameraDataService.save(request));
    }
}
