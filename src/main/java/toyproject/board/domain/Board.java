package toyproject.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import static org.springframework.util.StringUtils.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String explanation;
    @Lob
    private String notion;

    public Board(String title, String explanation, String notion) {
        this.title = title;
        this.explanation = explanation;
        this.notion = notion;
    }

    //==비즈니스 로직==//
    public void change(String explanation, String notion) {
        this.explanation = nullCheckExplanation(explanation);
        this.notion = nullCheckNotion(notion);
    }

    //==편의 메서드==//
    public String nullCheckExplanation(String explanation) {
        return hasText(explanation) ? explanation : this.explanation;
    }

    public String nullCheckNotion(String notion) {
        return hasText(notion) ? notion : this.notion;
    }
}
