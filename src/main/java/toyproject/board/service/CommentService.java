package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Comment;
import toyproject.board.domain.Member;
import toyproject.board.domain.Post;
import toyproject.board.dto.CommentDto;
import toyproject.board.exception.NoSuchException;
import toyproject.board.repository.CommentRepository;
import toyproject.board.repository.MemberRepository;
import toyproject.board.repository.PostRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

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

    /**
     * 댓글 조회
     */
    public List<CommentDto> searchComment(Long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        return commentList.stream()
                .map(CommentDto::new)
                .toList();
    }

    //댓글 신고

    private Comment getNewComment(CommentDto commentDto) {
        Member findMember = getMember(commentDto.getMemberId());
        Post findPost = getPost(commentDto.getPostId());
        findPost.addCommentCount();
        Comment findParentComment = getComment(commentDto.getParentCommentId());
        return new Comment(findMember, findPost, findParentComment, commentDto.getContent(), commentDto.isAnonymous());
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });
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
