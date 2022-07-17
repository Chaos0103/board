package toyproject.board.controller.form;

import lombok.Data;

@Data
public class CommentForm {

    private String content;
    private Boolean anonymous;
}
