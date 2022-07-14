package notice.notice;

import lombok.RequiredArgsConstructor;
import notice.notice.domain.Role;
import notice.notice.domain.User;
import notice.notice.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        public void dbInit1() {
            User user = User.builder()
                    .name("홍길동")
                    .email("@gmail.com")
                    .role(Role.USER)
                    .picture("afdasfa")
                    .build();

            em.persist(user);
        }


    }
}
