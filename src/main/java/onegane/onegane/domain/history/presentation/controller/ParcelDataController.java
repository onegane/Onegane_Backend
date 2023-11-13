package onegane.onegane.domain.history.presentation.controller;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.presentation.dto.request.ParcelInfoSaveRequest;
import onegane.onegane.domain.history.service.ParcelInfoSaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parcel")
public class ParcelDataController {

    private final ParcelInfoSaveService parcelInfoSaveService;

    @PostMapping
    public ResponseEntity<?> updateHistoryState(@RequestBody ParcelInfoSaveRequest request) {
        return parcelInfoSaveService.execute(request);
    }
}
