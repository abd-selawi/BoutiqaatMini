package boutiqaatMini.cart;

import boutiqaatMini.cartItem.CartItem;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService{

    CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public Cart update(UUID cartId, Cart cart) {
        Cart oldCart = cartRepository.findById(cartId).orElse(cart);

        try {
            for (Field field : cart.getClass().getDeclaredFields()) {
                if (!field.getName().equalsIgnoreCase("cartId") &&
                        Objects.nonNull(PropertyUtils.getProperty(cart, field.getName()))) {
                    PropertyUtils.setProperty(oldCart, field.getName(),
                            PropertyUtils.getProperty(cart, field.getName()));
                }
            }
        } catch (InvocationTargetException |
                 IllegalAccessException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }

        //update total price and quantity
        Double totalPrice= 0.00;
        int quantity=0;
        if (oldCart.getCartItems() != null) {
            List<CartItem> cartItems = oldCart.getCartItems();
            for (CartItem cartItem : cartItems) {
                totalPrice = totalPrice + (cartItem.getPrice());
                quantity = quantity + (cartItem.getQuantity());
            }
        }

        oldCart.setTotalPrice(totalPrice);
        oldCart.setQuantity(quantity);


        return cartRepository.save(oldCart);
    }
}
