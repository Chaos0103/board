package toyproject.board.dto;

import lombok.Data;
import toyproject.board.domain.Post;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long id;
    private Long boardId;
    private Long memberId;
    private String title;
    private String content;
    private Boolean anonymous;
    private int commentCount;
    private int goodCount;
    private LocalDateTime createdDate;

    private MemberDto memberDto;

    public PostDto(String title, String content, Boolean anonymous) {
        this.title = title;
        this.content = content;
        this.anonymous = anonymous;
    }

    public PostDto(Post post) {
        this.id = post.getId();
        this.boardId = post.getId();
        this.memberId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.anonymous = post.getAnonymous();
        this.commentCount = post.getCommentCount();
        this.goodCount = post.getGoodCount();
        this.createdDate = post.getCreatedDate();
        this.memberDto = new MemberDto(post.getMember());
    }
}
