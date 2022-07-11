package notice.notice.Service;

import notice.notice.domain.Category;
import notice.notice.domain.Role;
import notice.notice.domain.User;
import notice.notice.domain.board.Board;
import notice.notice.repository.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void 게시글작성() throws Exception {
        //given
        String title = "asdf";
        String content = "adfa";
        String writer = "adfas";

        User user = new User("adfa", "@gmail.com","asfas", Role.USER);

        Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .category()
                .user(user)
                .build();
        //when

        //then

    }
}
