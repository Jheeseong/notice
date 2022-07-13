package notice.notice.Dto;

import lombok.*;
import notice.notice.domain.Category;
import notice.notice.domain.Board;
import notice.notice.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;

    private String writer;

    private String title;

    private String content;

    private User user;

    private List<Category> categoryList;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .categoryList(categoryList)
                .content(content)
                .user(user)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String writer, String title, String content,User user, List<Category> categoryList,LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.user = user;
        this.categoryList = categoryList;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
