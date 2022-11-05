package boutiqaatMini.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@SuperBuilder
public class BrandModel implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty("english_brand_name")
    private String englishBrandName;

    @JsonProperty("arabic_brand_name")
    private String arBrandName;

    @JsonProperty("english_brand_description")
    private String enBrandDescription;

    @JsonProperty("arabic_brand_description")
    private String arBrandDescription;

//    private List<ProductModel> products;

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

//    public List<ProductModel> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<ProductModel> products) {
//        this.products = products;
//    }



    public BrandModel(Integer id, String englishBrandName, String arBrandName, String enBrandDescription, String arBrandDescription) {
        this.id = id;
        this.englishBrandName = englishBrandName;
        this.arBrandName = arBrandName;
        this.enBrandDescription = enBrandDescription;
        this.arBrandDescription = arBrandDescription;
//        this.products = products;
    }

    public BrandModel() {
    }
}
