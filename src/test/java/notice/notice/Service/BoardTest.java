package notice.notice.Service;

import notice.notice.domain.Board;
import notice.notice.domain.Role;
import notice.notice.domain.User;
import notice.notice.repository.BoardRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @After
    public void cleanup() {
        boardRepository.deleteAll();
    }

    @Test
    public void 게시글작성() throws Exception {
        //given
        String title = "asdf";
        String content = "adfa";
        String writer = "adfas";

        User user = new User("adfa", "@gmail.com","asfas", Role.USER);

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .user(user)
                .build()
        );

    //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);

        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
    }
}
