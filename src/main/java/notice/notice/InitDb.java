package notice.notice;

import lombok.RequiredArgsConstructor;
import notice.notice.domain.Address;
import notice.notice.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

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
                Member member = createMember("userA","1111", "서울", "1", "1111");
                em.persist(member);
            }

            private Member createMember(String idName, String pw, String city, String street, String zipcode) {
                Member member = new Member();
                member.setIdName(idName);
                member.setPw(pw);
                member.setAddress(new Address(city, street, zipcode));
                return member;
            }
        }
}
