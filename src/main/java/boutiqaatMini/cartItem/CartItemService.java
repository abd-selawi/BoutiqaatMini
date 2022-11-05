package boutiqaatMini.cartItem;

import java.util.Optional;

public interface CartItemService {

    Optional<CartItem> findById(Integer cartItemId);

    Optional<CartItem> addCartItem (Integer userId, Integer productId);

    Optional<CartItem> increaseQuantity (Integer cartItemId);

    Optional<CartItem> decreaseQuantity(Integer cartItemId);

    void deleteById(Integer cartItemId);

    void deleteAll(Integer userId);
}
