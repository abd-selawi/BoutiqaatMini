package boutiqaatMini.category;

import boutiqaatMini.Product.ProductModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;


@SuperBuilder
public class CategoryModel implements Serializable {

    //define fields
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty("english_category_name")
    private String enCategoryName;

    @JsonProperty("arabic_category_name")
    private String arCategoryName;

    @JsonProperty("english_category_description")
    private String enCategoryDescription;

    @JsonProperty("arabic_category_description")
    private String arCategoryDescription;

    @JsonProperty("parent_id")
    private Integer parentId;

    private List<ProductModel> products;

    public CategoryModel(Integer id, String enCategoryName, String arCategoryName, String enCategoryDescription, String arCategoryDescription, Integer parentId, List<ProductModel> products) {
        this.id = id;
        this.enCategoryName = enCategoryName;
        this.arCategoryName = arCategoryName;
        this.enCategoryDescription = enCategoryDescription;
        this.arCategoryDescription = arCategoryDescription;
        this.parentId = parentId;
        this.products = products;
    }

    public CategoryModel() {
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

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
