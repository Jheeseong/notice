package notice.notice.domain.board;

import lombok.Builder;
import lombok.Getter;
import notice.notice.domain.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
@Getter
public class Community extends Board{

}
