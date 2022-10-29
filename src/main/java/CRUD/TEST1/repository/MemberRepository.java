package CRUD.TEST1.repository;

import CRUD.TEST1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberName(String memberName);
}
