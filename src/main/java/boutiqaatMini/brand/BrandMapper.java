package boutiqaatMini.brand;

import boutiqaatMini.Product.Product;
import boutiqaatMini.Product.ProductModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BrandMapper {

//    @Mapping(target ="products",qualifiedByName ="products")
    BrandModel entityToModel (Brand entity);

    Brand modelToEntity (BrandModel model);

    @Named("products")
    @IterableMapping(qualifiedByName = "nonCyclicProduct")
    List<ProductModel> productList(List<Product> products);


    @Named("nonCyclicProduct")
    @Mapping(target = "categories", ignore = true)
//    @Mapping(target = "cartItems", ignore = true)
    @Mapping(target = "color", ignore = true)
    //@Mapping(target = "color.products", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "brand", ignore = true)
    ProductModel nonCyclicProduct (Product product);

    static BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

}
