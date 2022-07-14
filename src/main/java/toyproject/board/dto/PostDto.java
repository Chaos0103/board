package toyproject.board.dto;

import lombok.Data;

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

    public PostDto(String title, String content, Boolean anonymous) {
        this.title = title;
        this.content = content;
        this.anonymous = anonymous;
    }
}
