package boutiqaatMini.address;

import boutiqaatMini.order.Order;
import boutiqaatMini.order.OrderModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Mapping(target = "orders", qualifiedByName = "orders")
    @Mapping(target = "user", source = "user" ,ignore = true)
    AddressModel entityToModel (Address entity);
    Address modelToEntity (AddressModel model);


    @Named("orders")
    @IterableMapping(qualifiedByName = "nonCyclicOrder")
    List<OrderModel> orders(List<Order> orders);

    @Named("nonCyclicOrder")
    @Mapping(target = "address", ignore = true)
    OrderModel nonCyclicOrder(Order order);


    static AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

}
