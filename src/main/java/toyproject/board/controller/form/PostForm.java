package toyproject.board.controller.form;

import lombok.Data;

@Data
public class PostForm {

    private String title;
    private String content;
    private Boolean anonymous;
}
