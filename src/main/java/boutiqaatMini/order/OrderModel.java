package boutiqaatMini.order;

import boutiqaatMini.address.AddressModel;
import boutiqaatMini.orderItem.OrderItemModel;
import boutiqaatMini.user.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuperBuilder
public class OrderModel implements Serializable {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty("thanks")
    private String message;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date placedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updatedAt;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalPrice;

    private List<OrderItemModel> orderItems;


    private UserModel user;


    private AddressModel address;

    public OrderModel() {
    }

    public OrderModel(Integer id, String message, Date placedAt, Date updatedAt, Double totalPrice, List<OrderItemModel> orderItems, UserModel user, AddressModel address) {
        this.id = id;
        this.message = message;
        this.placedAt = placedAt;
        this.updatedAt = updatedAt;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
        this.user = user;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemModel> orderItems) {
        this.orderItems = orderItems;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
