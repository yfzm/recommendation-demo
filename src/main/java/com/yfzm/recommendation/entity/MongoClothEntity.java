package com.yfzm.recommendation.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "cloth")
public class MongoClothEntity {
    @Id
    private ObjectId id;
    private Integer cId;

    private Integer type;
    private ClothColor color;

    private List<Integer> tags;
    private List<Double> attr;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

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

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public List<Double> getAttr() {
        return attr;
    }

    public void setAttr(List<Double> attr) {
        this.attr = attr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoClothEntity)) return false;
        MongoClothEntity that = (MongoClothEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cId, that.cId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(color, that.color) &&
                Objects.equals(tags, that.tags) &&
                Objects.equals(attr, that.attr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cId, type, color, tags, attr);
    }
}
