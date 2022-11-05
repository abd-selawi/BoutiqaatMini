package boutiqaatMini.cartItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartItemRestController {

    private CartItemService cartItemService;

    @Autowired
    public CartItemRestController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @PostMapping("/cartitem/add-item/user/{userId}/product/{productId}")
    public ResponseEntity<?> addCartItem(@PathVariable Integer userId,@PathVariable Integer productId) {

        Optional<?> cartItem = cartItemService.addCartItem(userId, productId).map(CartItemMapper.INSTANCE::entityToModel);
        return ResponseEntity.ok(cartItem);

    }

    @PutMapping("/cartitem/increase/{cartItemId}")
    public ResponseEntity<?> increaseQuantity(@PathVariable Integer cartItemId){

       Optional<?> cartItem = cartItemService.increaseQuantity(cartItemId).map(CartItemMapper.INSTANCE::entityToModel);
       return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/cartitem/decrease/{cartItemId}")
    public ResponseEntity<?> decreaseQuantity(@PathVariable Integer cartItemId){

        Optional<?> cartItem = cartItemService.decreaseQuantity(cartItemId).map(CartItemMapper.INSTANCE::entityToModel);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/cartitem/{cartItemId}")
    public ResponseEntity<?> getProduct(@PathVariable Integer cartItemId) {

        Optional<?> cartItem = cartItemService.findById(cartItemId).map(CartItemMapper.INSTANCE::entityToModel);

        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/cartitem/{cartItemId}")
    public String deleteCartItemById(@PathVariable Integer cartItemId) {

        try {
            Optional<?> cartItem = cartItemService.findById(cartItemId);

            cartItemService.deleteById(cartItemId);

            return "Deleted cartItem id - " + cartItemId;

        } catch (RuntimeException e) {
            return ("cartItem Id not found - " + cartItemId);
        }
    }


    @DeleteMapping("/cartitem/user/{userId}")
    public String deleteAllCartItemsByUserId(@PathVariable Integer userId) {

        try {
            cartItemService.deleteAll(userId);

            return "Deleted cartItems for userId - " + userId;

        } catch (RuntimeException e) {
            return ("userId not found - " + userId);
        }
    }

}
