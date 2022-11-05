package boutiqaatMini.cart;

import boutiqaatMini.cartItem.CartItemModel;
import boutiqaatMini.user.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@SuperBuilder
public class CartModel implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date dateCreated;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date dateUpdated;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalPrice;

    @JsonProperty("quantity")
    private int quantity;


    private UserModel user;
    private List<CartItemModel> cartItems;

    public List<CartItemModel> getCartItems() {
        return cartItems;
    }

    public CartModel() {
    }

    public CartModel(UUID id, Boolean isActive, Date dateCreated, Date dateUpdated, Double totalPrice, int itemsNumber, UserModel user, List<CartItemModel> cartItems) {
        this.id = id;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.user = user;
        this.cartItems = cartItems;
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

    public void setQuantity(int itemsNumber) {
        this.quantity = itemsNumber;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setCartItems(List<CartItemModel> cartItems) {
        this.cartItems = cartItems;
    }
}

