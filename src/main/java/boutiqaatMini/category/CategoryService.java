package boutiqaatMini.category;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public List<CategoryModel> findAll(Pageable pageable);

    public Optional<?> findById(int theId);

    public Optional<CategoryModel> save(CategoryModel categoryModel);

    public CategoryModel update(Integer categoryId, CategoryModel categoryModel);

    public void deleteById(int theId);
}
