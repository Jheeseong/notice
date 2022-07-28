package notice.notice.Dto;

import lombok.*;
import notice.notice.domain.Board;
import notice.notice.domain.Comment;
import notice.notice.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDto {

    private Long id;

    @NotBlank
    @Length(max = 100)
    private String contents;

    private Board board;

    private User user;

    public Comment toEntity() {
        Comment comment = Comment.builder()
                .id(id)
                .contents(contents)
                .user(user)
                .board(board)
                .build();
        return comment;
    }
}
