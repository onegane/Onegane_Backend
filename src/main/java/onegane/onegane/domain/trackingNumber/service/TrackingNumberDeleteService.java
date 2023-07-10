package onegane.onegane.domain.trackingNumber.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.trackingNumber.domain.TrackingNumber;
import onegane.onegane.domain.trackingNumber.presentation.dto.TrackingNumberRequestDto;
import onegane.onegane.domain.trackingNumber.repository.TrackingNumberRepository;
import onegane.onegane.domain.user.repository.UserRepository;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class TrackingNumberDeleteService {

    private final TrackingNumberRepository trackingNumberRepository;

    @Transactional
    public ResponseEntity delete(Long id) {
        System.out.println("asdf");
        System.out.println(id);
        trackingNumberRepository.deleteById(id);
        return ResponseEntity.ok("success");
    }
}
