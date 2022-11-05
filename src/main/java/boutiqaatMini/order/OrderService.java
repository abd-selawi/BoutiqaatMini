package boutiqaatMini.order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> checkout(Integer userId, Integer addressId);
}
