package notice.notice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String title;

    private String content;


}
