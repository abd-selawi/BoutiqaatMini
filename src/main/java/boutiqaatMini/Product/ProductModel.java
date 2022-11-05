package boutiqaatMini.Product;

import boutiqaatMini.brand.BrandModel;
import boutiqaatMini.category.CategoryModel;
import boutiqaatMini.color.ColorModel;
import boutiqaatMini.tag.TagModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@SuperBuilder
public class ProductModel implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty("english_product_name")
    private String enProductName;

    @JsonProperty("arabic_product_name")
    private String arProductName;

    @JsonProperty("english_product_description")
    private String enProductDescription;

    @JsonProperty("arabic_product_description")
    private String arProductDescription;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("product_price")
    private Double productPrice;


    @JsonProperty("size")
    private java.lang.String size;


    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date dateCreated;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date dateModified;

    private BrandModel brand;
    private ColorModel color;
    private List<CategoryModel> categories;
    private List<TagModel> tags;
//    private List<CartItemModel> cartItems;
//    private List<OrderItemModel> orderItems;



    public ProductModel() {
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setEnProductName(String enProductName) {
        this.enProductName = enProductName;
    }

    public void setArProductName(String arProductName) {
        this.arProductName = arProductName;
    }

    public void setEnProductDescription(String enProductDescription) {
        this.enProductDescription = enProductDescription;
    }

    public void setBrand(BrandModel brand) {
        this.brand = brand;
    }

    public BrandModel getBrand() {
        return brand;
    }

    public void setArProductDescription(String arProductDescription) {
        this.arProductDescription = arProductDescription;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }



    public void setSize(String size) {
        this.size = size;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsActive() {
        return isActive;
    }


    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }

    public void setColor(ColorModel color) {
        this.color = color;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

//    public List<CartItemModel> getCartItems() {
//        return cartItems;
//    }
//
//    public void setCartItems(List<CartItemModel> cartItems) {
//        this.cartItems = cartItems;
//    }

    public String getEnProductName() {
        return enProductName;
    }

    public String getArProductName() {
        return arProductName;
    }

    public String getEnProductDescription() {
        return enProductDescription;
    }

    public String getArProductDescription() {
        return arProductDescription;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getProductPrice() {
        return productPrice;
    }


    public String getSize() {
        return size;
    }


    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public ColorModel getColor() {
        return color;
    }

    public List<TagModel> getTags() {
        return tags;
    }
//
//    public List<OrderItemModel> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItemModel> orderItems) {
//        this.orderItems = orderItems;
//    }
}
