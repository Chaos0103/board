package toyproject.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "comment_id")
    private Comment parent;

    @Lob
    private String content;
    private Boolean anonymous;
    private int goodCount;

    public Comment(Post post, Comment parent, String content, Boolean anonymous) {
        this.post = post;
        this.parent = parent;
        this.content = content;
        this.anonymous = anonymous;
        this.goodCount = 0;
    }

    //==비즈니스 로직==//
    public void addGoodCount() {
        this.goodCount++;
    }
}
