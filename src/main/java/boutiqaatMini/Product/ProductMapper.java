package boutiqaatMini.Product;

import boutiqaatMini.cart.CartMapper;
import boutiqaatMini.category.Category;
import boutiqaatMini.category.CategoryModel;
import boutiqaatMini.order.OrderMapper;
import boutiqaatMini.orderItem.OrderItemMapper;
import boutiqaatMini.tag.Tag;
import boutiqaatMini.tag.TagModel;
import boutiqaatMini.user.UserMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, CartMapper.class, OrderItemMapper.class, OrderMapper.class})
public interface ProductMapper {

//    @Mapping(target = "brand", source = "brand" ,ignore = true)
//    @Mapping(target = "color", source = "color" ,ignore = true)
    @Mapping(target = "categories", qualifiedByName = "categories")
    @Mapping(target = "tags", qualifiedByName = "tags")
//    @Mapping(target = "cartItems", qualifiedByName = "cartItems")
//    @Mapping(target = "orderItems", qualifiedByName = "orderItems")
    ProductModel entityToModel(Product entity);
    Product modelToEntity(ProductModel model);


    @Named("categories")
    @IterableMapping(qualifiedByName = "nonCyclicCategory")
    List<CategoryModel> categories(List<Category> categories);

    @Named("nonCyclicCategory")
    @Mapping(target = "products", ignore = true)
    CategoryModel nonCyclicCategory(Category category);



    @Named("tags")
    @IterableMapping(qualifiedByName = "nonCyclicTag")
    List<TagModel> tags (List<Tag> tags);

    @Named("nonCyclicTag")
    @Mapping(target = "products", ignore = true)
    TagModel nonCyclicTag(Tag tag);



//    @Named("cartItems")
//    @IterableMapping(qualifiedByName = "nonCyclicCartItem")
//    List<CartItemModel> cartItems (List<CartItem> cartItemModel);
//
//    @Named("nonCyclicCartItem")
//    @Mapping(target = "product", ignore = true)
//    CartItemModel nonCyclicCartItem(CartItem cartItem);




//    @Named("orderItems")
//    @IterableMapping(qualifiedByName = "nonCyclicCartItem")
//    List<OrderItemModel> orderItems (List<OrderItem> orderItemModel);
//
//    @Named("nonCyclicCartItem")
//    @Mapping(target = "product", ignore = true)
//    OrderItemModel nonCyclicTag(OrderItem orderItem);




    static ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
}
