package notice.notice.Dto;

import lombok.*;
import notice.notice.domain.Category;
import notice.notice.domain.Board;
import notice.notice.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;

    @NotBlank(message = "작성자를 적어주세요.")
    private String writer;

    @NotBlank(message = "제목을 적어주세요.")
    @Size(max = 20, message = "제목을 20자 이내로 작성해주세요.")
    private String title;

    @NotEmpty(message = "내용을 적어주세요.")
    private String content;

    private User user;

    @NotNull(message = "카테고리를 선택해주세요.")
    private Long categoryId;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .categoryId(categoryId)
                .content(content)
                .user(user)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String writer, String title, String content,User user, Long categoryId,LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.user = user;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
