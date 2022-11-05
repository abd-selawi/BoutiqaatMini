package boutiqaatMini.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product,Integer> {



    Page<Product> findByBrandId(Integer brandId, Pageable pageable);
//
////    List<Product> findByCategoryIdOrBrandId(Integer categoryId, Integer brandId, Sort sort);
//
    Page<Product> findByCategoriesId(Integer categoryId, Pageable pageable);
//
////    List<Product> findByCategoryIdAndBrandId(Integer categoryId, Integer brandId, Sort sort);
//


}
