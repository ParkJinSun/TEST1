package CRUD.TEST1.repository;

import CRUD.TEST1.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);
    Optional<Board> findByWriter(String writer);

}
