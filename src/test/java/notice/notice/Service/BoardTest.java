package notice.notice.Service;

import notice.notice.Dto.BoardDto;
import notice.notice.domain.Board;
import notice.notice.domain.Role;
import notice.notice.domain.User;
import notice.notice.repository.BoardRepository;
import notice.notice.service.BoardService;
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

    @Autowired
    BoardService boardService;

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
        assertThat(board.getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    public void 카테고리분류() throws Exception {
        //given
        String title1 = "asdf";
        String content1 = "adfa";
        String writer1 = "adfas";

        String title2 = "asdf2";
        String content2 = "adfa2";
        String writer2 = "adfas2";

        String title3 = "asdf3";
        String content3 = "adfa3";
        String writer3 = "adfas3";


        User user = new User("adfa", "@gmail.com","asfas", Role.USER);

        boardRepository.save(Board.builder()
                .title(title1)
                .content(content1)
                .writer(writer1)
                .categoryId(1L)
                .build()
        );

        /*boardRepository.save(Board.builder()
                .title(title2)
                .content(content2)
                .writer(writer2)
                .categoryId(2L)
                .build()
        );
        boardRepository.save(Board.builder()
                .title(title3)
                .content(content3)
                .writer(writer3)
                .categoryId(3L)
                .build()
        );*/
        //when
        List<BoardDto> boardList = boardService.getBoardCategoryList(1, 1L);

        //then

        for (BoardDto boardDto : boardList) {
            System.out.println(boardDto);
        }

        BoardDto board = boardList.get(0);

        assertThat(board.getTitle()).isEqualTo(title1);
        assertThat(board.getContent()).isEqualTo(content1);
        assertThat(board.getCategoryId()).isEqualTo(1L);
    }
}
