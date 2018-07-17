package dto.score;

import com.yfzm.recommendation.entity.ClothColor;

import java.util.List;

public class ClothAttributes {
    private Integer type;
    private ClothColor color;
    private List<Double> attrs;
    private List<String> tags;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ClothColor getColor() {
        return color;
    }

    public void setColor(ClothColor color) {
        this.color = color;
    }

    public List<Double> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Double> attrs) {
        this.attrs = attrs;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
