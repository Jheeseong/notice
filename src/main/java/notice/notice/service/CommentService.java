package notice.notice.service;

import lombok.AllArgsConstructor;;
import notice.notice.Dto.BoardDto;
import notice.notice.Dto.CommentDto;
import notice.notice.domain.Board;
import notice.notice.domain.Comment;
import notice.notice.domain.User;
import notice.notice.repository.BoardRepository;
import notice.notice.repository.CommentRepository;
import notice.notice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private CommentDto convertEntityToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .contents(comment.getContents())
                .board(comment.getBoard())
                .user(comment.getUser())
                .build();
    }

    @Transactional
    public Long createComment(Long boardId, CommentDto commentDto, String email) {
        Board board = boardRepository.findById(boardId).get();

        User user = userRepository.findByEmail(email).get();

        commentDto.setBoard(board);
        commentDto.setUser(user);

        return commentRepository.save(commentDto.toEntity()).getId();

    }

    @Transactional
    public List<CommentDto> getCommentList(BoardDto boardDto) {
        List<Comment> commentList = boardDto.getCommentList();
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentDtoList.add(this.convertEntityToDto(comment));
        }
        return commentDtoList;
    }
}
