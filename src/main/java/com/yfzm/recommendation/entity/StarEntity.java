package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "star", schema = "recommendation")
@IdClass(StarEntityPK.class)
public class StarEntity {
    private int userId;
    private int tweetId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "tweet_id", nullable = false)
    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarEntity that = (StarEntity) o;
        return userId == that.userId &&
                tweetId == that.tweetId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, tweetId);
    }
}
