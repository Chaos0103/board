package toyproject.board.dto;

import lombok.Data;
import toyproject.board.domain.Member;

@Data
public class MemberDto {

    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;

    public MemberDto(String loginId, String password, String name, String phone, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.nickname = member.getNickname();
    }
}
