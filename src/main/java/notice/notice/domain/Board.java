package notice.notice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import notice.notice.domain.Category;
import notice.notice.domain.Time;
import notice.notice.domain.User;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board extends Time {

    @Id // PK
    @GeneratedValue(strategy= GenerationType.IDENTITY)  // PK의 생성 규칙
    private Long id;

    //@Column(length = 10, nullable = false)
    private String writer;

    //@Column(length = 100, nullable = false)
    private String title;

    //@Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long categoryId;

    @OrderBy("id desc")
    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    // Java 디자인 패턴, 생성 시점에 값을 채워줌
    @Builder
    public Board(Long id, String title, String content, String writer, User user, Long categoryId, List<Comment> commentList) {
        // Assert 구문으로 안전한 객체 생성 패턴을 구현
        Assert.hasText(writer, "writer must not be empty");
        Assert.hasText(title, "title must not be empty");
        Assert.hasText(content, "content must not be empty");

        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.user = user;
        this.categoryId = categoryId;
        this.commentList = commentList;
    }

}
