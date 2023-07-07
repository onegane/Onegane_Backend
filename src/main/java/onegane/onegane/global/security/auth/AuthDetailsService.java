package onegane.onegane.global.security.auth;

import lombok.RequiredArgsConstructor;
import onegane.onegane.domain.user.domain.User;
import onegane.onegane.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByEmail(username);

        if (user.isPresent()) {
            return new AuthDetails(user.get());
        } else {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다");
        }
    }
}
