package boutiqaatMini.category;

import boutiqaatMini.Product.Product;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;


@Builder
@Entity
@Table(name = "category")
public class Category {

    //define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="english_category_name")
    private String enCategoryName;

    @Column(name="arabic_category_name")
    private String arCategoryName;

    @Column(name="english_category_description")
    private String enCategoryDescription;

    @Column(name="arabic_category_description")
    private String arCategoryDescription;

    @Column(name = "parent_id")
    private Integer parentId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},
            mappedBy = "categories")
    private List<Product> products;


    public Category(Integer id, String enCategoryName, String arCategoryName, String enCategoryDescription, String arCategoryDescription, Integer parentId, List<Product> products) {
        this.id = id;
        this.enCategoryName = enCategoryName;
        this.arCategoryName = arCategoryName;
        this.enCategoryDescription = enCategoryDescription;
        this.arCategoryDescription = arCategoryDescription;
        this.parentId = parentId;
        this.products = products;
    }

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnCategoryName() {
        return enCategoryName;
    }

    public void setEnCategoryName(String enCategoryName) {
        this.enCategoryName = enCategoryName;
    }

    public String getArCategoryName() {
        return arCategoryName;
    }

    public void setArCategoryName(String arCategoryName) {
        this.arCategoryName = arCategoryName;
    }

    public String getEnCategoryDescription() {
        return enCategoryDescription;
    }

    public void setEnCategoryDescription(String enCategoryDescription) {
        this.enCategoryDescription = enCategoryDescription;
    }

    public String getArCategoryDescription() {
        return arCategoryDescription;
    }

    public void setArCategoryDescription(String arCategoryDescription) {
        this.arCategoryDescription = arCategoryDescription;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", enCategoryName='" + enCategoryName + '\'' +
                ", arCategoryName='" + arCategoryName + '\'' +
                ", enCategoryDescription='" + enCategoryDescription + '\'' +
                ", arCategoryDescription='" + arCategoryDescription + '\'' +
                ", parentId=" + parentId +
                ", products=" + products +
                '}';
    }
}
