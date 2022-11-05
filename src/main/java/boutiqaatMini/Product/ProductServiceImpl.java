package boutiqaatMini.Product;

import boutiqaatMini.brand.BrandRepository;
import boutiqaatMini.category.CategoryRepository;
import boutiqaatMini.tag.TagRepository;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    CategoryRepository categoryRepository;

    BrandRepository brandRepository;

    TagRepository tagRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository, TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.tagRepository = tagRepository;
    }




    @Override
    public Optional<ProductModel> findById(Integer theId) {
        return Optional.ofNullable(productRepository.findById(theId).map(ProductMapper.INSTANCE::entityToModel).orElseThrow(
                () -> new NoSuchElementException("Did not find Product id - ")));
    }




    @Override
    public Optional<ProductModel> save(ProductModel productModel) {
        return Optional.ofNullable(ProductMapper.INSTANCE
                .entityToModel(productRepository.save(ProductMapper.INSTANCE.modelToEntity(productModel))));
    }

    @Override
    public ProductModel update(Integer productId, ProductModel productModel) {

        ProductModel oldProduct = productRepository.findById(productId)
                .map(ProductMapper.INSTANCE::entityToModel).orElse(productModel);

        try {

            for (Field field : productModel.getClass().getDeclaredFields()){
                if(!field.getName().equalsIgnoreCase("productId") &&
                        (Objects.nonNull(PropertyUtils.getProperty(productModel, field.getName())))){
                    PropertyUtils.setProperty(oldProduct, field.getName(),
                            PropertyUtils.getProperty(productModel, field.getName()));
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }

        return ProductMapper.INSTANCE
                .entityToModel(productRepository.save(ProductMapper.INSTANCE.modelToEntity(oldProduct)));
    }

    @Override
    public void deleteById(int theId) {
        productRepository.deleteById(theId);

    }
}
