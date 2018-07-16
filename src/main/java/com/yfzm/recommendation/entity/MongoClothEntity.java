package com.yfzm.recommendation.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cloth")
public class MongoClothEntity {
    @Id
    private ObjectId id;

    private Integer cId;
    private List<Integer> tags;
    private ModelClothItem attr;

//    private Integer type;
//    private ClothColor color;
//    private List<Double> texture;
//    private List<Double> fabric;
//    private List<Double> shape;
//    private List<Double> part;
//    private List<Double> style;

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

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public ModelClothItem getAttr() {
        return attr;
    }

    public void setAttr(ModelClothItem attr) {
        this.attr = attr;
    }
}
