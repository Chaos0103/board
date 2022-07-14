package toyproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.board.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
