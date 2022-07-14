package toyproject.board.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "comment_id")
    private Comment child;

    @Lob
    private String content;
    private Boolean anonymous;
    private int goodCount;
}
