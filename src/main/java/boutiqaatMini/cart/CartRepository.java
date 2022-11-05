package boutiqaatMini.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    boolean existsByUserId(Integer id);

}
