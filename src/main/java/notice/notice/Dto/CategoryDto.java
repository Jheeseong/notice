package notice.notice.Dto;

import lombok.*;
import notice.notice.domain.Category;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    private Long parentId;

    private List<CategoryDto> subCategories;

    @Builder
    public CategoryDto(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}
