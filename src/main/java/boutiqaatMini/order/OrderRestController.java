package boutiqaatMini.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("checkout/user/{userId}/address/{addressId}")
    public ResponseEntity<?> checkout (@PathVariable Integer userId, @PathVariable Integer addressId){

        Optional <OrderModel> orderModel = orderService.checkout(userId , addressId).map(OrderMapper.INSTANCE::entityToModel);

        return ResponseEntity.ok(orderModel);
    }
}
