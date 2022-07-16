package notice.notice.service;

import lombok.RequiredArgsConstructor;
import notice.notice.Dto.BoardDto;
import notice.notice.Dto.CategoryDto;
import notice.notice.domain.Board;
import notice.notice.domain.Category;
import notice.notice.repository.BoardRepository;
import notice.notice.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;


    private CategoryDto convertEntityToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParentId())
                .build();
    }
    @Transactional
    public CategoryDto createCategory() {
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll()
                .stream()
                .map(ce -> new CategoryDto(ce.getId(), ce.getName(), ce.getParentId()))
                .collect(groupingBy(cd -> cd.getParentId()));

        CategoryDto rootCategoryDto = new CategoryDto(0L, "ROOT", null);
        addSubCategories(rootCategoryDto, groupingByParent);

        return rootCategoryDto;
    }

    @Transactional
    public List<CategoryDto> AllCategory() {
        List<Category> categoryEntities = categoryRepository.findAll();
        List<CategoryDto> categoryList = new ArrayList<>();

        if (categoryEntities.isEmpty()) return categoryList;


        for (Category category : categoryEntities) {
            categoryList.add(this.convertEntityToDto(category));
        }

        return categoryList;
    }

    private void addSubCategories(CategoryDto parent, Map<Long, List<CategoryDto>> groupingByParentId) {
        List<CategoryDto> subCategories = groupingByParentId.get(parent.getId());

        if (subCategories == null) {
            return;
        }

        parent.setSubCategories(subCategories);

        subCategories.stream()
                .forEach(s -> {
                    addSubCategories(s, groupingByParentId);
                });
    }
}
