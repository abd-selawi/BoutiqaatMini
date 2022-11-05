package boutiqaatMini.tag;

import boutiqaatMini.Product.Product;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "tag")
public class Tag {

    //define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name="english_tag_name")
    private String enTagName;

    @NotBlank
    @Column(name="arabic_tag_name")
    private String arTagName;

    @Column(name="english_tag_description")
    private String enTagDescription;

    @Column(name="arabic_tag_description")
    private String arTagDescription;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            mappedBy = "tags")
    private List<Product> products;


    public Tag(){
    }

    public Tag(Integer id, String enTagName, String arTagName, String enTagDescription, String arTagDescription, List<Product> products) {
        this.id = id;
        this.enTagName = enTagName;
        this.arTagName = arTagName;
        this.enTagDescription = enTagDescription;
        this.arTagDescription = arTagDescription;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnTagName() {
        return enTagName;
    }

    public void setEnTagName(String enTagName) {
        this.enTagName = enTagName;
    }

    public String getArTagName() {
        return arTagName;
    }

    public void setArTagName(String arTagName) {
        this.arTagName = arTagName;
    }

    public String getEnTagDescription() {
        return enTagDescription;
    }

    public void setEnTagDescription(String enTagDescription) {
        this.enTagDescription = enTagDescription;
    }

    public String getArTagDescription() {
        return arTagDescription;
    }

    public void setArTagDescription(String arTagDescription) {
        this.arTagDescription = arTagDescription;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", enTagName='" + enTagName + '\'' +
                ", arTagName='" + arTagName + '\'' +
                ", enTagDescription='" + enTagDescription + '\'' +
                ", arTagDescription='" + arTagDescription + '\'' +
                '}';
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // add a convenience method

    public void addProduct(Product theProduct) {

        if (products == null) {
            products = new ArrayList<>();
        }

        products.add(theProduct);
    }

}
