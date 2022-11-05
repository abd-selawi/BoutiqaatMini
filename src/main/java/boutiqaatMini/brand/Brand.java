package boutiqaatMini.brand;

import boutiqaatMini.Product.Product;
import lombok.Builder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Builder
@Entity
@Table(name= "brand")
public class Brand {

    //define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="english_brand_name")
    private String englishBrandName;

    @Column(name="arabic_brand_name")
    private String arBrandName;

    @Column(name="english_brand_description")
    private String enBrandDescription;

    @Column(name="arabic_brand_description")
    private String arBrandDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand",
            cascade =CascadeType.ALL)
    private List<Product> products;



    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void add(Product tempProduct){

        if(products == null){
            products = new ArrayList<>();
        }

        products.add(tempProduct);
        tempProduct.setBrand(this);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", enBrandName='" + englishBrandName + '\'' +
                ", arBrandName='" + arBrandName + '\'' +
                ", enBrandDescription='" + enBrandDescription + '\'' +
                ", arBrandDescription='" + arBrandDescription + '\'' +
                ", products=" + products +
                '}';
    }

    public Brand(Integer id, String englishBrandName, String arBrandName, String enBrandDescription, String arBrandDescription, List<Product> products) {
        this.id = id;
        this.englishBrandName = englishBrandName;
        this.arBrandName = arBrandName;
        this.enBrandDescription = enBrandDescription;
        this.arBrandDescription = arBrandDescription;
        this.products = products;
    }

    public Brand() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglishBrandName() {
        return englishBrandName;
    }

    public void setEnglishBrandName(String englishBrandName) {
        this.englishBrandName = englishBrandName;
    }

    public String getArBrandName() {
        return arBrandName;
    }

    public void setArBrandName(String arBrandName) {
        this.arBrandName = arBrandName;
    }

    public String getEnBrandDescription() {
        return enBrandDescription;
    }

    public void setEnBrandDescription(String enBrandDescription) {
        this.enBrandDescription = enBrandDescription;
    }

    public String getArBrandDescription() {
        return arBrandDescription;
    }

    public void setArBrandDescription(String arBrandDescription) {
        this.arBrandDescription = arBrandDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Brand brand = (Brand) o;
        return id != null && Objects.equals(id, brand.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
