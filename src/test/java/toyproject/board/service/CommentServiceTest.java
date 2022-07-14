package toyproject.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Comment;
import toyproject.board.domain.Post;
import toyproject.board.dto.CommentDto;
import toyproject.board.repository.CommentRepository;
import toyproject.board.repository.PostRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired CommentService commentService;
    @Autowired CommentRepository commentRepository;
    @Autowired PostRepository postRepository;

    @Test
    void createComment() {
        Post post = getPost();
        CommentDto commentDto = new CommentDto("test comment", true);
        commentDto.setPostId(post.getId());

        Long commentId = commentService.createComment(commentDto);

        Optional<Comment> findComment = commentRepository.findById(commentId);
        assertThat(findComment).isPresent();
    }

    @Test
    void removeComment() {
        Comment comment = new Comment(null, null, "test comment", true);
        Comment savedComment = commentRepository.save(comment);

        commentService.removeComment(savedComment.getId());

        Optional<Comment> findComment = commentRepository.findById(savedComment.getId());
        assertThat(findComment).isEmpty();
    }

    @Test
    void goodComment() {
        Comment comment = new Comment(null, null, "test comment", true);
        Comment savedComment = commentRepository.save(comment);

        Long commentId = commentService.goodComment(savedComment.getId());

        Comment findComment = commentRepository.findById(commentId).get();
        assertThat(findComment.getGoodCount()).isEqualTo(1);
    }

    private Post getPost() {
        Post post = new Post(null, null, "test post title", "teet post content", true);
        return postRepository.save(post);
    }
}