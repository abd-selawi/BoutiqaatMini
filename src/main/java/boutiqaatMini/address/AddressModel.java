package boutiqaatMini.address;

import boutiqaatMini.order.OrderModel;
import boutiqaatMini.user.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;


@ToString

@SuperBuilder
public class AddressModel implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;

    @JsonProperty("phone")
    private String phone;

    private UserModel user;

    private List<OrderModel> orders;

    public AddressModel() {
    }

    public AddressModel(Integer id, String city, String street, String phone, UserModel user, List<OrderModel> orders) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.user = user;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
