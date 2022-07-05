package notice.notice.Service;

import notice.notice.domain.Address;
import notice.notice.domain.Board;
import notice.notice.domain.Member;
import notice.notice.repository.BoardRepository;
import notice.notice.service.BoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 게시판작성() throws Exception {
        Member member = createMember();

        Board board = Board.createBoard(member,"adfa","adsfa");

        Long boardId = boardService.join(member.getId(), board.getTitle(), board.getContent());

        em.flush();
        Board getBoard = boardRepository.findOne(boardId);

        System.out.println("Board= " + getBoard.toString());
        Assertions.assertEquals(board, getBoard);
    }

    private Member createMember() {
        Member member = new Member();
        member.setIdName("asdf");
        member.setPw("adfa");
        member.setAddress(new Address("1234", "서울","경기도"));
        member.setRegDate(LocalDateTime.now());
        em.persist(member);
        return member;
    }
}
