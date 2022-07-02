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

        Long boardId = boardService.join(board);

        Board getBoard = boardRepository.findOne(boardId);

        Assertions.assertEquals(boardId, getBoard);
    }

    private Member createMember() {
        Member member = new Member();
        member.setIdName("asdf");
        member.setPw("adfa");
        member.setAddress(new Address("1234", "서울","경기도"));
        em.persist(member);
        return member;
    }
}
