package toyproject.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String nickname;

    public Member(String loginId, String password, String name, String phone, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
    }
}
