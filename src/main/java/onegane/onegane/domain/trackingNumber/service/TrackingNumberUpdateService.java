package onegane.onegane.domain.trackingNumber.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.trackingNumber.domain.TrackingNumber;
import onegane.onegane.domain.trackingNumber.presentation.dto.TrackingNumberRequestDto;
import onegane.onegane.domain.trackingNumber.repository.TrackingNumberRepository;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.domain.user.repository.UserRepository;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class TrackingNumberUpdateService {

    private final TrackingNumberRepository trackingNumberRepository;

    @Transactional
    public ResponseEntity update(Long id, TrackingNumberRequestDto dto) {
        TrackingNumber trackingNumber = trackingNumberRepository.findById(id).get();
        trackingNumberRepository.save(trackingNumber.update(dto.getTrackingNumber(), dto.getNickname()));
        return ResponseEntity.ok("success");
    }
}
