package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Comment;
import toyproject.board.domain.Post;
import toyproject.board.dto.CommentDto;
import toyproject.board.exception.NoSuchException;
import toyproject.board.repository.CommentRepository;
import toyproject.board.repository.PostRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /**
     * 댓글 등록
     */
    public Long createComment(CommentDto commentDto) {
        Comment newComment = getNewComment(commentDto);
        Comment savedComment = commentRepository.save(newComment);
        return savedComment.getId();
    }

    /**
     * 댓글 삭제
     */
    public void removeComment(Long commentId) {
        commentRepository.delete(getComment(commentId));
    }

    /**
     * 댓글 추천
     */
    public Long goodComment(Long commentId) {
        Comment findComment = getComment(commentId);
        findComment.addGoodCount();
        return findComment.getId();
    }

    //댓글 신고

    private Comment getNewComment(CommentDto commentDto) {
        Post findPost = getPost(commentDto.getPostId());
        Comment findParentComment = getComment(commentDto.getParentCommentId());
        return new Comment(findPost, findParentComment, commentDto.getContent(), commentDto.isAnonymous());
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 게시물입니다.");
        });
    }

    private Comment getComment(Long commentId) {
        if (commentId == null) {
            return null;
        }
        return commentRepository.findById(commentId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 댓글입니다.");
        });
    }
}
