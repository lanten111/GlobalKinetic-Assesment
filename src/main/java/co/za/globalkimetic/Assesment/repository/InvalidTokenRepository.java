package co.za.globalkimetic.Assesment.repository;

import co.za.globalkimetic.Assesment.domain.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidTokenRepository extends JpaRepository<InvalidToken, Long> {
    Boolean existsByToken(String token);
}
