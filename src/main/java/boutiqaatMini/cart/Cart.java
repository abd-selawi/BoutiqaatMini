package boutiqaatMini.cart;

import boutiqaatMini.cartItem.CartItem;
import boutiqaatMini.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;


@ToString
@Setter
@Getter
@Builder
@Entity
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date dateUpdated;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade ={CascadeType.ALL})
    private List<CartItem> cartItems;





    public Cart() {
    }

    public Cart(UUID id, Boolean isActive, Date dateCreated, Date dateUpdated, Double totalPrice, int itemsNumber, User user, List<CartItem> cartItems) {
        this.id = id;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.totalPrice = totalPrice;
        this.quantity = itemsNumber;
        this.user = user;
        this.cartItems = cartItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cart cart = (Cart) o;
        return id != null && Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
