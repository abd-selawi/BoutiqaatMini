package boutiqaatMini.orderItem;

import boutiqaatMini.Product.ProductModel;
import boutiqaatMini.order.OrderModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OrderItemModel implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("price")
    private Double price;


    private ProductModel product;

    private OrderModel order;

    public OrderItemModel() {
    }

    public OrderItemModel(Integer id, Integer quantity, Double price, ProductModel product, OrderModel order) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
