package notice.notice.Dto;

import lombok.*;
import notice.notice.domain.Category;
import notice.notice.domain.board.Board;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;

    private String writer;

    private String title;

    private String content;

    private Category category;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .category(category)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String writer, String title, String content, Category category,LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
