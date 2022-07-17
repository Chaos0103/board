package toyproject.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Lob
    private String content;
    private Boolean anonymous;
    private int goodCount;

    public Comment(Member member, Post post, String content, Boolean anonymous) {
        this.member = member;
        this.post = post;
        this.content = content;
        this.anonymous = anonymous;
        this.goodCount = 0;
    }

    //==비즈니스 로직==//
    public void addGoodCount() {
        this.goodCount++;
    }
}
