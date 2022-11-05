package boutiqaatMini.user;

import boutiqaatMini.address.Address;
import boutiqaatMini.address.AddressModel;
import boutiqaatMini.order.Order;
import boutiqaatMini.order.OrderModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    @Mapping(target = "cart", source = "cart" ,ignore = true)
    @Mapping(target = "addresses", qualifiedByName = "addresses")
    @Mapping(target = "orders", qualifiedByName = "orders")
    UserModel entityToModel (User entity);

    User modelToEntity (UserModel model);



    @Named("addresses")
    @IterableMapping(qualifiedByName = "nonCyclicAddress")
    List<AddressModel> addresses (List<Address> addresses);

    @Named("nonCyclicAddress")
    @Mapping(target = "user", ignore = true)
    AddressModel nonCyclicAddress (Address address);



    @Named("orders")
    @IterableMapping(qualifiedByName = "nonCyclicOrder")
    List<OrderModel> orders(List<Order> orders);

    @Named("nonCyclicOrder")
    @Mapping(target = "user", ignore = true)
    OrderModel nonCyclicOrder(Order order);




    static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
