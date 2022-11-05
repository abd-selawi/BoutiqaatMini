package boutiqaatMini.user;

import boutiqaatMini.cart.Cart;
import boutiqaatMini.cart.CartMapper;
import boutiqaatMini.cart.CartModel;
import boutiqaatMini.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    CartRepository cartRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public Optional<UserModel> CreateUser(UserModel userModel) {

        User userEntity = userRepository.save(UserMapper.INSTANCE.modelToEntity(userModel));

        //creating the user
        UserModel user = Optional.ofNullable(UserMapper.INSTANCE
                .entityToModel(userEntity))
                .orElseThrow(()->new NoSuchElementException("Couldn't create user"));

        //creating user's cart
        if (user.getCart() == null){

            CartModel cartModel = new CartModel();
            cartModel.setIsActive(true);
            cartModel.setQuantity(0);
            cartModel.setTotalPrice(0.00);
            Date date = new Date();
            cartModel.setDateCreated(date);
            cartModel.setDateUpdated(date);
            cartModel.setUser(user);
            Cart cartEntity = CartMapper.INSTANCE.modelToEntity(cartModel);
            cartEntity.setUser(userEntity);
            cartRepository.save(cartEntity);
        }
        return Optional.ofNullable(user);
    }
}
