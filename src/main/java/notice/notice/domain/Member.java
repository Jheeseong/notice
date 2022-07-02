package notice.notice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String idName;

    @NotEmpty
    private String pw;

    @Embedded
    private Address address;

    private LocalDateTime regDate;

    @JsonIgnore
    @OneToMany(mappedBy = "writer")
    private List<Board> boards = new ArrayList<>();





}
