package com.yfzm.recommendation.entity;

import java.util.List;
import java.util.Objects;

/**
 * - type: Integer (0 for upper, 1 for lower)
 * - texture (Double Array)
 * - fabric (Double Array)
 * - shape (Double Array)
 * - part (Double Array)
 * - style (Double Array)
 * - color (Color)
 * - r (Integer)
 * - g (Integer)
 * - b (Integer)
 */
public class ModelClothItem {
    private Integer type;
    private ClothColor color;
    private List<Double> texture;
    private List<Double> fabric;
    private List<Double> shape;
    private List<Double> part;
    private List<Double> style;

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

    public List<Double> getTexture() {
        return texture;
    }

    public void setTexture(List<Double> texture) {
        this.texture = texture;
    }

    public List<Double> getFabric() {
        return fabric;
    }

    public void setFabric(List<Double> fabric) {
        this.fabric = fabric;
    }

    public List<Double> getShape() {
        return shape;
    }

    public void setShape(List<Double> shape) {
        this.shape = shape;
    }

    public List<Double> getPart() {
        return part;
    }

    public void setPart(List<Double> part) {
        this.part = part;
    }

    public List<Double> getStyle() {
        return style;
    }

    public void setStyle(List<Double> style) {
        this.style = style;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelClothItem clothItem = (ModelClothItem) o;
        return Objects.equals(type, clothItem.type) &&
                Objects.equals(color, clothItem.color) &&
                Objects.equals(texture, clothItem.texture) &&
                Objects.equals(fabric, clothItem.fabric) &&
                Objects.equals(shape, clothItem.shape) &&
                Objects.equals(part, clothItem.part) &&
                Objects.equals(style, clothItem.style);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, color, texture, fabric, shape, part, style);
    }

}
