package co.za.globalkimetic.Assesment.repository;

import co.za.globalkimetic.Assesment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String username);

    Boolean existsByUserName(String username);
}
