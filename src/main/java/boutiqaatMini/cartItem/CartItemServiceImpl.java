package boutiqaatMini.cartItem;

import boutiqaatMini.Product.Product;
import boutiqaatMini.Product.ProductRepository;
import boutiqaatMini.cart.Cart;
import boutiqaatMini.cart.CartRepository;
import boutiqaatMini.cart.CartService;
import boutiqaatMini.user.User;
import boutiqaatMini.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private CartService cartService;
    private UserRepository userRepository;


    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, ProductRepository productRepository, CartRepository cartRepository, CartService cartService, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<CartItem> findById(Integer cartItemId){
        return Optional.ofNullable(cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new NoSuchElementException("Did not find Product id - ")));
    }



    // Add Item if quantity available, if item already existing add quantity
    @Override
    public Optional<CartItem> addCartItem(Integer userId, Integer productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Incorrect Product Id: " + productId));

        UUID cartId = userRepository.findById(userId).get().getCart().getId();

        //Check if Stock available
        if (product.getStock() > 0 ){
            CartItem cartItem = cartItemRepository.findByProductIdAndCartId(productId, cartId)
                    .orElseGet(() -> {
                        CartItem tempCartItem = new CartItem();
                        Cart cart = cartRepository.findById(cartId)
                                .orElseThrow(() -> new RuntimeException("Incorrect cart Id: " + cartId));
                        tempCartItem.setQuantity(0); //Quantity will be added below
                        tempCartItem.setCart(cart);
                        tempCartItem.setProduct(product);
                        cartItemRepository.saveAndFlush(tempCartItem);

                        //saving entity
                        cart.getCartItems().add(tempCartItem);
                        cartRepository.save(cart);
                        return tempCartItem;
                    });

            // increase quantity from 0 to 1 if new, increase by 1 if Old
            Integer cartItemId = cartItem.getId() ;
            increaseQuantity(cartItemId);
            return Optional.ofNullable(cartItemRepository.save(cartItem));

        }
        throw new RuntimeException ("Try less Quantity!");
    }

    @Override
    @Transactional
    public Optional<CartItem> increaseQuantity(Integer cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Incorrect cart item Id: " + cartItemId));

        //Check if Stock available
        if (cartItem.getProduct().getStock() > 0) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            //update price
            cartItem.setPrice(cartItem.getProduct().getProductPrice() * cartItem.getQuantity());
            cartItemRepository.save(cartItem);

        } else {
            throw new RuntimeException("Try less Quantity!");
        }

        //update Cart
        cartService.update(cartItem.getCart().getId(), cartItem.getCart());

        return Optional.ofNullable(cartItemRepository.save(cartItem));
    }


    @Override
    public Optional<CartItem> decreaseQuantity(Integer cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Incorrect cart item Id: " + cartItemId));

        //remove Item if Quantity equals 1
        if (cartItem.getQuantity() > 1){
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItem.setPrice(cartItem.getProduct().getProductPrice()*cartItem.getQuantity());
            cartItemRepository.save(cartItem);

            //update Cart
            cartService.update(cartItem.getCart().getId(), cartItem.getCart());

        }else{
            deleteById(cartItemId);
        }

//        //update price
//        cartItem.setPrice(cartItem.getProduct().getProductPrice()*cartItem.getQuantity());



        return Optional.ofNullable(cartItem);
    }


    @Override
    public void deleteById(Integer cartItemId) {

        Optional <CartItem> cartItem = cartItemRepository.findById(cartItemId);

        Optional<Cart> cart = cartRepository.findById(cartItem.get().getCart().getId());

        cartItemRepository.deleteById(cartItemId);

        //update Cart
        cartService.update(cart.get().getId(), cartItem.get().getCart());
    }

    @Override
    public void deleteAll(Integer userId){

        Optional<User> user = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("can't find User Id")));

        Cart cart =user.get().getCart();


//        Cart cart = cartRepository.findById(cartId)
//                .orElseThrow(() -> new RuntimeException("Incorrect cart Id: " + cartId));

        cartItemRepository.deleteAll(new ArrayList<>(cart.getCartItems()));

        //update Cart
        cartService.update(cart.getId(), cart);
    }
}
