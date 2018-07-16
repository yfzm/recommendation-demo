package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_colloc", schema = "recommendation", catalog = "")
@IdClass(UserCollocEntityPK.class)
public class UserCollocEntity {
//    @EmbeddedId
//    private UserCollocEntityPK id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @MapsId("userId")
    @Id
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @MapsId("collocationId")
    @Id
    @JoinColumn(name = "collocation_id", referencedColumnName = "collocation_id")
    private CollocationEntity collocation;

    @Column(name = "collection_time")
    private Timestamp collectionTime;

//    public UserCollocEntityPK getId() {
//        return id;
//    }
//
//    public void setId(UserCollocEntityPK id) {
//        this.id = id;
//    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CollocationEntity getCollocation() {
        return collocation;
    }

    public void setCollocation(CollocationEntity collocation) {
        this.collocation = collocation;
    }

    public Timestamp getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Timestamp collectionTime) {
        this.collectionTime = collectionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCollocEntity that = (UserCollocEntity) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(collocation, that.collocation) &&
                Objects.equals(collectionTime, that.collectionTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, collocation, collectionTime);
    }
}
