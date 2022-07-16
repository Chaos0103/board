package toyproject.board.controller.form;

import lombok.Data;

@Data
public class MemberForm {

    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;
}
