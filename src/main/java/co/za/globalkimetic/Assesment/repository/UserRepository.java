package co.za.globalkimetic.Assesment.repository;

import co.za.globalkimetic.Assesment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);
}
