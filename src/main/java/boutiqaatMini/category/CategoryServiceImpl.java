package boutiqaatMini.category;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository theCategoryRepository) {
        categoryRepository = theCategoryRepository;
    }

    @Override
    public List<CategoryModel> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream().map(CategoryMapper.INSTANCE::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Optional<?> findById(int theId) {
        return Optional.ofNullable(categoryRepository.findById(theId).map(CategoryMapper.INSTANCE::entityToModel).orElseThrow(
                () -> new NoSuchElementException("Did not find Category id - ")));
    }



    @Override
    public Optional<CategoryModel> save(CategoryModel categoryModel) {


        return Optional.ofNullable(CategoryMapper.INSTANCE
                .entityToModel(categoryRepository.save(CategoryMapper.INSTANCE.modelToEntity(categoryModel))));
    }

    @Override
    public CategoryModel update(Integer categoryId, CategoryModel categoryModel) {
        CategoryModel oldCategory = categoryRepository.findById(categoryId)
                .map(CategoryMapper.INSTANCE::entityToModel).orElse(categoryModel);

        try {

            for (Field field : categoryModel.getClass().getDeclaredFields()){
                if(!field.getName().equalsIgnoreCase("categoryId") &&
                        Objects.nonNull(PropertyUtils.getProperty(categoryModel, field.getName()))){
                    PropertyUtils.setProperty(oldCategory, field.getName(),
                            PropertyUtils.getProperty(categoryModel, field.getName()));
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }

        return CategoryMapper.INSTANCE
                .entityToModel(categoryRepository.save(CategoryMapper.INSTANCE.modelToEntity(oldCategory)));
    }


    @Override
    public void deleteById(int theId) {
        categoryRepository.deleteById(theId);
    }
}
