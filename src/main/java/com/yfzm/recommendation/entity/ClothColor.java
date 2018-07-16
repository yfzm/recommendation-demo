package com.yfzm.recommendation.entity;

import java.util.Objects;

/**
 * - r (Integer)
 * - g (Integer)
 * - b (Integer)
 */
public class ClothColor {
    private Integer r;
    private Integer g;
    private Integer b;

    public ClothColor() {
    }

    public ClothColor(Integer r, Integer g, Integer b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClothColor that = (ClothColor) o;
        return Objects.equals(r, that.r) &&
                Objects.equals(g, that.g) &&
                Objects.equals(b, that.b);
    }

    @Override
    public int hashCode() {

        return Objects.hash(r, g, b);
    }
}
