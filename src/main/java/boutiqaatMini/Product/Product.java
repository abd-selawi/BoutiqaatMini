package boutiqaatMini.Product;


import boutiqaatMini.brand.Brand;
import boutiqaatMini.cartItem.CartItem;
import boutiqaatMini.category.Category;
import boutiqaatMini.color.Color;
import boutiqaatMini.orderItem.OrderItem;
import boutiqaatMini.tag.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@DynamicUpdate
@EntityListeners({AuditingEntityListener.class})
@Table(name="product")
public class Product {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "english_product_name")
    private String enProductName;

    @Column(name = "arabic_product_name")
    private String arProductName;

    @Column(name = "english_product_description")
    private String enProductDescription;

    @Column(name = "arabic_product_description")
    private String arProductDescription;

    @JoinColumn(name = "stock")
    @Min(value = 0, message = "*Stock has to be non negative number")
    private Integer stock;

    @Column(name = "product_price")
    private Double productPrice;

    @Column (name = "size")
    private java.lang.String size;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "date_created", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date dateCreated;

    @Column(name = "date_modified", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date dateModified;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH ,CascadeType.PERSIST})
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "tag_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @OneToMany(mappedBy="product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy="product")
    private List<OrderItem> orderItems;

    public Product() {
    }

    public Product(Integer id, String enProductName, String arProductName, String enProductDescription, String arProductDescription, Integer stock, Double productPrice, String size, Boolean isActive, Date dateCreated, Date dateModified, Brand brand, Color color, List<Category> categories, List<Tag> tags, List<CartItem> cartItems, List<OrderItem> orderItems) {
        this.id = id;
        this.enProductName = enProductName;
        this.arProductName = arProductName;
        this.enProductDescription = enProductDescription;
        this.arProductDescription = arProductDescription;
        this.stock = stock;
        this.productPrice = productPrice;
        this.size = size;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.brand = brand;
        this.color = color;
        this.categories = categories;
        this.tags = tags;
        this.cartItems = cartItems;
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", enProductName='" + enProductName + '\'' +
                ", arProductName='" + arProductName + '\'' +
                ", enProductDescription='" + enProductDescription + '\'' +
                ", arProductDescription='" + arProductDescription + '\'' +
                ", stock=" + stock +
                ", productPrice=" + productPrice +
                ", size='" + size + '\'' +
                ", getIsActive=" + isActive +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", brand=" + brand +
                ", color=" + color +
                ", categories=" + categories +
                ", tags=" + tags +
                ", cartItems=" + cartItems +
                ", orderItems=" + orderItems +
                '}';
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getEnProductName() {
        return enProductName;
    }

    public void setEnProductName(String enProductName) {
        this.enProductName = enProductName;
    }

    public String getArProductName() {
        return arProductName;
    }

    public void setArProductName(String arProductName) {
        this.arProductName = arProductName;
    }

    public String getEnProductDescription() {
        return enProductDescription;
    }

    public void setEnProductDescription(String enProductDescription) {
        this.enProductDescription = enProductDescription;
    }

    public String getArProductDescription() {
        return arProductDescription;
    }

    public void setArProductDescription(String arProductDescription) {
        this.arProductDescription = arProductDescription;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }


    public String getSize() {
        return size;
    }


    public void setSize(String size) {
        this.size = size;
    }


    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    // add a convenience method

    public void addCategory(Category theCategory) {

        this.categories.add(theCategory);
        theCategory.getProducts().add(this);
    }

    public void removeCategory(int categoryId) {
        Category theCategory = this.categories.stream().filter(t -> t.getId() == categoryId).findFirst().orElse(null);
        if (theCategory != null) {
            this.tags.remove(theCategory);
            theCategory.getProducts().remove(this);
        }
    }
}
