package notice.notice.service;

import lombok.RequiredArgsConstructor;
import notice.notice.domain.Board;
import notice.notice.domain.Member;
import notice.notice.repository.BoardRepository;
import notice.notice.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Long memberId, String title, String content) {
        Member member = memberRepository.findOne(memberId);
        Board board = Board.createBoard(member, title, content);
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
