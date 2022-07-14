package toyproject.board.dto;

import lombok.Data;

@Data
public class BoardDto {

    private Long id;
    private String title;
    private String explanation;
    private String notion;

    public BoardDto(String title, String explanation, String notion) {
        this.title = title;
        this.explanation = explanation;
        this.notion = notion;
    }
}
