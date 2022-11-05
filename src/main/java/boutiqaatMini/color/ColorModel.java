package boutiqaatMini.color;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@SuperBuilder
public class ColorModel implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty("arabic_color")
    private String arColor;

    @JsonProperty("english_color")
    private String enColor;


    public ColorModel(int id, String arColor, String enColor) {
        this.id = id;
        this.arColor = arColor;
        this.enColor = enColor;
//        this.products = products;
    }

    public ColorModel() {
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

}
