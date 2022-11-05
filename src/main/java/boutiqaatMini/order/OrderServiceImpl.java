package boutiqaatMini.order;

import boutiqaatMini.Product.Product;
import boutiqaatMini.Product.ProductRepository;
import boutiqaatMini.Product.ProductService;
import boutiqaatMini.address.Address;
import boutiqaatMini.address.AddressRepository;
import boutiqaatMini.cart.Cart;
import boutiqaatMini.cartItem.CartItem;
import boutiqaatMini.cartItem.CartItemService;
import boutiqaatMini.orderItem.OrderItem;
import boutiqaatMini.orderItem.OrderItemRepository;
import boutiqaatMini.user.User;
import boutiqaatMini.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    OrderItemRepository orderItemRepository;
    CartItemService cartItemService;
    OrderRepository orderRepository;
    ProductService productService;
    UserRepository userRepository;
    AddressRepository addressRepository;
    ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderItemRepository orderItemRepository, CartItemService cartItemService, OrderRepository orderRepository, ProductService productService, UserRepository userRepository, AddressRepository addressRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.cartItemService = cartItemService;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
    }


    //Check if enough stock available
    @Transient
    public Boolean  validateCartItemStock (List<CartItem> cartItems) {

        boolean valid = false;

        for (CartItem cartItem : cartItems) {

            //Check if Available Stock
            if (cartItem.getProduct().getStock() >= cartItem.getQuantity()) {
                valid = true;

                // if one item has no stock, return false and break
            } else {
                valid = false;
                break;
            }
        }
        return valid;
    }

//    // generate Order No.
//    public String GenerateOrderNo() {
//        final Random random = new Random();
//        final Set<Integer> intSet = new HashSet<>();
//        while (intSet.size() < 20) {
//            intSet.add(random.nextInt(50) + 1);
//        }
//        final int[] numbers = new int[intSet.size()];
//        final Iterator<Integer> iter = intSet.iterator();
//        for (int i = 0; iter.hasNext(); ++i) {
//            numbers[i] = iter.next();
//        }
//        return Arrays.toString(numbers);
//    }

    @Override
    @Transactional
    public Optional<Order> checkout(Integer userId, Integer addressId) {

        //get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Did not find User id - "));

        Cart cart = user.getCart();

        List<CartItem> cartItems = cart.getCartItems();

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NoSuchElementException("Did not find address id"));


        // Check if Available Stock is there
        Order order = null;
        if (validateCartItemStock(cartItems)) {

            order = new Order();
            order.setMessage("Your Order Will be delivered in 3 working days");
            order.setTotalPrice(cart.getTotalPrice());
            order.setUser(user);
            order.setAddress(address);
            orderRepository.save(order);

            for (CartItem cartItem : cartItems) {

                //creating orderItem and cloning it with cartItem
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getPrice());
                orderItem.setOrder(order);

                orderItemRepository.save(orderItem);

                Product product = orderItem.getProduct();
                product.setStock( (product.getStock()) - (orderItem.getQuantity()) );

                productRepository.save(product);
            }

            cartItemService.deleteAll(userId);

        }

        return Optional.ofNullable(order);

    }
}






