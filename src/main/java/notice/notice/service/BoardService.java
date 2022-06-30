package notice.notice.service;

import lombok.RequiredArgsConstructor;
import notice.notice.domain.Board;
import notice.notice.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long join(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public List<Board> findByTitle(String title) {
        return boardRepository.findByTitle(title);
    }

    @Transactional
    public void update(Long id, String title) {
        Board board = boardRepository.findOne(id);
        board.setTitle(title);
    }
}
