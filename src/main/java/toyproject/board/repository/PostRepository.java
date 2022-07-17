package toyproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import toyproject.board.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p join fetch p.member m where p.board.id = :boardId")
    List<Post> findByBoardId(@Param("boardId") Long boardId);

    @Query("select p from Post p join fetch p.member m where p.id = :postId")
    Post findContentById(@Param("postId") Long postId);
}
