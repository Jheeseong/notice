package notice.notice.Dto;

import lombok.*;
import notice.notice.domain.Board;
import notice.notice.domain.Comment;
import notice.notice.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;

    @NotBlank
    @Length(max = 100)
    private String contents;

    private Board board;

    private User user;

    private LocalDateTime createdTime;

    public Comment toEntity() {
        Comment comment = Comment.builder()
                .id(id)
                .contents(contents)
                .user(user)
                .board(board)
                .build();
        return comment;
    }

    @Builder
    public CommentDto(Long id, String contents, Board board, User user, LocalDateTime createdTime) {
        this.id = id;
        this.contents = contents;
        this.board = board;
        this.user = user;
        this.createdTime = createdTime;
    }
}
