package notice.notice.repository;

import notice.notice.domain.board.Board;
import notice.notice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleContaining(String keyword);

    Page<Board> findByCategoryContaining(Pageable pageable, String keyword);

    Board findByUser(User user);
}
