package notice.notice.domain.board;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Q")
@Getter
public class Qna {
}
