package notice.notice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @NotEmpty
    private String title;

    private String content;

    @NotEmpty
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    private LocalDateTime regDate;

    private Long viewCnt;

    //연관관계 메서드
    public void setMember(Member writer) {
        this.writer = writer;
        writer.getIdName();
    }

    public static Board createBoard(Member member, String title, String content) {
        Board board = new Board();
        board.setMember(member);
        board.setTitle(title);
        board.setContent(content);
        board.setRegDate(LocalDateTime.now());
        board.setViewCnt(10L);
        return board;
    }

}
