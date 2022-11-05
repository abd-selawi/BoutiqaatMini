package boutiqaatMini.tag;

import boutiqaatMini.Product.ProductModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class TagModel implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty("english_tag_name")
    private String enTagName;

    @JsonProperty("arabic_tag_name")
    private String arTagName;

    @JsonProperty("english_tag_description")
    private String enTagDescription;

    @JsonProperty("arabic_tag_description")
    private String arTagDescription;

    private List<ProductModel> products;

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

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public TagModel() {
    }

    public TagModel(int id, String enTagName, String arTagName, String enTagDescription, String arTagDescription, List<ProductModel> products) {
        this.id = id;
        this.enTagName = enTagName;
        this.arTagName = arTagName;
        this.enTagDescription = enTagDescription;
        this.arTagDescription = arTagDescription;
        this.products = products;
    }
}
