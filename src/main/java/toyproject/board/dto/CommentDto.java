package toyproject.board.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private Long postId;
    private Long parentCommentId;
    private String content;
    private boolean anonymous;
    private int goodCount;

    public CommentDto(String content, boolean anonymous) {
        this.content = content;
        this.anonymous = anonymous;
    }
}
