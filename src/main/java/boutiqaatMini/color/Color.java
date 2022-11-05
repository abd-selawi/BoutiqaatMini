package boutiqaatMini.color;

import boutiqaatMini.Product.Product;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Builder
@Entity
@Table(name= "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="arabic_color")
    private String arColor;

    @Column(name="english_color")
    private String enColor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "color",
            cascade ={CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Product> products;

    public Color(int id, String arColor, String enColor, List<Product> products) {
        this.id = id;
        this.arColor = arColor;
        this.enColor = enColor;
        this.products = products;
    }

    public Color() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArColor() {
        return arColor;
    }

    public void setArColor(String arColor) {
        this.arColor = arColor;
    }

    public String getEnColor() {
        return enColor;
    }

    public void setEnColor(String enColor) {
        this.enColor = enColor;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Color{" +
                "id=" + id +
                ", arColor='" + arColor + '\'' +
                ", enColor='" + enColor + '\'' +
                ", products=" + products +
                '}';
    }

    public void add(Product tempProduct){

        if(products == null){
            products = new ArrayList<>();
        }

        products.add(tempProduct);
        tempProduct.setColor(this);
    }

}
