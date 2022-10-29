package CRUD.TEST1.repository;

import CRUD.TEST1.domain.Member;
import CRUD.TEST1.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByMember(Member member);
}
