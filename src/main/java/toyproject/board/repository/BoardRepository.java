package toyproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
