package boutiqaatMini.order;

import boutiqaatMini.address.Address;
import boutiqaatMini.orderItem.OrderItem;
import boutiqaatMini.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ToString
@Entity
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
@Table(name ="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "thanks")
    private String message;


    @Column(name = "date_created")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date placedAt;

    @Column(name = "date_updated")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade ={CascadeType.ALL})
    private List<OrderItem> orderItems;

    @ManyToOne( fetch = FetchType.LAZY ,cascade ={CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne( fetch = FetchType.LAZY ,cascade ={CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

//    total price --> function
//
//    Order Items:
//    order
//    products
//    quantity
//    unit_price


    public Order() {
    }

    public Order(Integer id, String message, Date placedAt, Date updatedAt, Double totalPrice, List<OrderItem> orderItems, User user, Address address) {
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(message, order.message) && Objects.equals(placedAt, order.placedAt) && Objects.equals(updatedAt, order.updatedAt) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(orderItems, order.orderItems) && Objects.equals(user, order.user) && Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, placedAt, updatedAt, totalPrice, orderItems, user, address);
    }
}
