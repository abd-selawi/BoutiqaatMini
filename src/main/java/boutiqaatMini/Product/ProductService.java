package boutiqaatMini.Product;

import java.util.Optional;

public interface ProductService {

//    public List<ProductModel> findAll(Integer categoryId, Integer brandId, Integer tagId);

//    List<ProductModel> findAll(Integer categoryId, Integer brandId, Integer tagId, Pageable pageable);
//
    public Optional<ProductModel> findById(Integer theId);

    public Optional<ProductModel> save(ProductModel productModel);

    public ProductModel update(Integer brandId, ProductModel productModel);

    public void deleteById(int theId);
}
