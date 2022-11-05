package boutiqaatMini.orderItem;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemMapper {

    @Mapping(target = "product", source = "product" ,ignore = true)
    @Mapping(target = "order", source = "order" ,ignore = true)
    OrderItemModel entityToModel (OrderItem entity);

    OrderItem modelToEntity (OrderItemModel model);

    static OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

}
