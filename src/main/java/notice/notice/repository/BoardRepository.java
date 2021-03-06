package notice.notice.repository;

import notice.notice.domain.Board;
import notice.notice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleContaining(String keyword);

    Board findByUser(User user);

    Page<Board> findByCategoryId(Pageable pageable, Long id);
}
