package boutiqaatMini.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

//    @Query(
//            value = "select  from cart_item c where c.cart_id = :productId and c.product_id= :cartId" ,
//            nativeQuery = true
//    )
    Optional<CartItem> findByProductIdAndCartId(
            @Param("productId") Integer productId,
            @Param("cartId") UUID cartId);


}
