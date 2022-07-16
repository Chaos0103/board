package toyproject.board.dto;

import lombok.Data;
import toyproject.board.domain.Board;

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

    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.explanation = board.getExplanation();
        this.notion = board.getNotion();
    }
}
