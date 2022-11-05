package boutiqaatMini.order;

import boutiqaatMini.orderItem.OrderItem;
import boutiqaatMini.orderItem.OrderItemMapper;
import boutiqaatMini.orderItem.OrderItemModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "orderItems", qualifiedByName = "orderItems")
    @Mapping(target = "user", source = "user" ,ignore = true)
    @Mapping(target = "address", source = "address" ,ignore = true)
    OrderModel entityToModel (Order entity);

    Order modelToEntity (OrderModel model);


    @Named("orderItems")
    @IterableMapping(qualifiedByName = "nonCyclicOrderItem")
    List<OrderItemModel> orderItems(List<OrderItem> orderItems);

    @Named("nonCyclicOrderItem")
    @Mapping(target = "order", ignore = true)
    OrderItemModel nonCyclicOrderItem(OrderItem orderItem);

    static OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}
