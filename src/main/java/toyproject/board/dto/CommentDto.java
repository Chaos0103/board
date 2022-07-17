package toyproject.board.dto;

import lombok.Data;
import toyproject.board.domain.Comment;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private Long id;
    private Long memberId;
    private Long postId;
    private String content;
    private boolean anonymous;
    private int goodCount;
    private LocalDateTime createdDate;

    private MemberDto memberDto;

    public CommentDto(String content, boolean anonymous) {
        this.content = content;
        this.anonymous = anonymous;
    }

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.memberId = comment.getMember().getId();
        this.postId = comment.getPost().getId();
        this.content = comment.getContent();
        this.anonymous = comment.getAnonymous();
        this.goodCount = comment.getGoodCount();
        this.createdDate = comment.getCreatedDate();
        this.memberDto = new MemberDto(comment.getMember());
    }
}
