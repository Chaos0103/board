package toyproject.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static org.springframework.util.StringUtils.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String title;
    @Lob
    private String content;
    private Boolean anonymous;
    private int commentCount;
    private int goodCount;

    public Post(Member member, Board board, String title, String content, Boolean anonymous, int commentCount, int goodCount) {
        this.member = member;
        this.board = board;
        this.title = title;
        this.content = content;
        this.anonymous = anonymous;
        this.commentCount = commentCount;
        this.goodCount = goodCount;
    }

    //==비즈니스 로직==//
    public void change(String title, String content, Boolean anonymous) {
        this.title = nullCheckTitle(title);
        this.content = nullCheckContent(content);
        this.anonymous = anonymous;
    }

    public void addGoodCount() {
        this.goodCount++;
    }

    //==편의 메서드==//
    private String nullCheckTitle(String title) {
        return hasText(title) ? title : this.title;
    }

    private String nullCheckContent(String content) {
        return hasText(content) ? content : this.content;
    }
}
