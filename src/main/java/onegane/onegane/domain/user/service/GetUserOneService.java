package onegane.onegane.domain.user.service;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserOneService {

    private final UserRepository userRepository;

    public User execute(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
