package boutiqaatMini.cartItem;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartItemMapper {

//    @Mapping(target = "product.cartItems", ignore = true)
//    @Mapping(target = "product.brand", ignore = true)
//    @Mapping(target = "product.categories", ignore = true)
//    @Mapping(target = "product.tags", ignore = true)
//    @Mapping(target = "cart.cartItems", ignore = true)
//    @Mapping(target = "cart.orderHeader", ignore = true)
//    @Mapping(target = "orderItem.cartItem", ignore = true)
//    @Mapping(target = "orderItem.orderHeader", ignore = true
    @Mapping(target = "product", source = "product" ,ignore = true)

    @Mapping(target = "cart", source = "cart" ,ignore = true)
    CartItemModel entityToModel(CartItem entity);

    CartItem modelToEntity(CartItemModel model);

    static CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);
}
