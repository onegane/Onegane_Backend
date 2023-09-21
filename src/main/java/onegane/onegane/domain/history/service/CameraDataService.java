package onegane.onegane.domain.history.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.history.presentation.dto.CameraDataRequest;
import onegane.onegane.domain.history.repository.CameraDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CameraDataService {

    private final CameraDataRepository cameraDataRepository;

    public String save(CameraDataRequest request) {
        return cameraDataRepository.save(request.toEntity()).getTrackingNumber();
    }
}
