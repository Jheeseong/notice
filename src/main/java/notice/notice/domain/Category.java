package notice.notice.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
public class Category extends Time{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long parentId;

    public Category(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }



}
