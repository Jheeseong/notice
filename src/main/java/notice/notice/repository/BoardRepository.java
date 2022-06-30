package notice.notice.repository;

import lombok.RequiredArgsConstructor;
import notice.notice.domain.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public List<Board> findAll() {
        return em.createQuery("select b form Board b", Board.class)
                .getResultList();
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findByTitle(String title) {
        return em.createQuery("select b from Board b where b.title = :title", Board.class)
                .setParameter("title", title)
                .getResultList();
    }
}
