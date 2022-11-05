package boutiqaatMini.color;

import boutiqaatMini.Product.Product;
import boutiqaatMini.Product.ProductModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ColorMapper {

//    @Mapping(target ="products",qualifiedByName ="products")
    ColorModel entityToModel (Color entity);

    Color modelToEntity (ColorModel model);

    @Named("products")
    @IterableMapping(qualifiedByName = "nonCyclicProduct")
    List<ProductModel> products(List<Product> products);

    @Named("nonCyclicProduct")
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "tags", ignore = true)
    ProductModel nonCyclicProduct (Product product);

    static ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);
}

