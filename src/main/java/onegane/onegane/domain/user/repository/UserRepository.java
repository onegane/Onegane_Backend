package onegane.onegane.domain.user.repository;

import onegane.onegane.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByStudentNumber(Long number);

    Optional<User> findByEmail(String email);
}
