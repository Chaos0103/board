package toyproject.board.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Board extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String explanation;
    @Lob
    private String notion;
}
