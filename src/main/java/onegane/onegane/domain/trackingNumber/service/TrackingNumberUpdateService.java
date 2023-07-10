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
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public ResponseEntity update(HttpServletRequest request, TrackingNumberRequestDto dto) {
        String accessToken = request.getHeader("Authorization").split(" ")[1].trim();
        String email = jwtProvider.extractEmail(accessToken);
        Long userId = userRepository.findByEmail(email).get().getId();
        TrackingNumber trackingNumber = trackingNumberRepository.findByUserId(userId).get();
        trackingNumberRepository.save(trackingNumber.update(dto.getTrackingNumber(), dto.getNickname()));
        return ResponseEntity.ok("success");
    }
}
