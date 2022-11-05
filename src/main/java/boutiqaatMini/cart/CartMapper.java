package boutiqaatMini.cart;

import boutiqaatMini.cartItem.CartItem;
import boutiqaatMini.cartItem.CartItemModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartMapper {

    @Mapping(target = "user", source = "user", ignore = true)
    @Mapping(target = "cartItems", qualifiedByName = "cartItems")
    CartModel entityToModel(Cart entity);

    Cart modelToEntity(CartModel model);

    @Named("cartItems")
    @IterableMapping(qualifiedByName = "nonCyclicCartItem")
    List<CartItemModel> CartItems(List<CartItem> cartItems);

    @Named("nonCyclicCartItem")

    @Mapping(target = "cart.user.addresses", ignore = true)
//    @Mapping(target = "product.cartItems", ignore = true)
    @Mapping(target = "product.brand", ignore = true)
    @Mapping(target = "product.categories", ignore = true )
    @Mapping(target = "product.tags", ignore = true )
    @Mapping(target = "cart", ignore = true)
    CartItemModel nonCyclicCartItem(CartItem cartItem);






    static CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
}
