package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "collocation", schema = "mashion", catalog = "")
public class CollocationEntity {
    private int collocationId;
    private String picUrl;
    private String upperId;
    private String lowerId;

    private Set<UserCollocEntity> userAssoc;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collocation_id", nullable = false)
    public int getCollocationId() {
        return collocationId;
    }

    public void setCollocationId(int collocationId) {
        this.collocationId = collocationId;
    }

    @Basic
    @Column(name = "pic_url", nullable = false)
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Basic
    @Column(name = "upper_id", nullable = false)
    public String getUpperId() {
        return upperId;
    }

    public void setUpperId(String upperId) {
        this.upperId = upperId;
    }

    @Basic
    @Column(name = "lower_id", nullable = false)
    public String getLowerId() {
        return lowerId;
    }

    public void setLowerId(String lowerId) {
        this.lowerId = lowerId;
    }

    @OneToMany(mappedBy = "user")
    public Set<UserCollocEntity> getUserAssoc() {
        return userAssoc;
    }

    public void setUserAssoc(Set<UserCollocEntity> userAssoc) {
        this.userAssoc = userAssoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollocationEntity that = (CollocationEntity) o;
        return collocationId == that.collocationId &&
                Objects.equals(picUrl, that.picUrl) &&
                Objects.equals(upperId, that.upperId) &&
                Objects.equals(lowerId, that.lowerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(collocationId, picUrl, upperId, lowerId);
    }
}
