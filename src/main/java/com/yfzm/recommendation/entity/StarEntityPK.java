package com.yfzm.recommendation.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StarEntityPK implements Serializable {
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
        StarEntityPK that = (StarEntityPK) o;
        return userId == that.userId &&
                tweetId == that.tweetId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, tweetId);
    }
}
