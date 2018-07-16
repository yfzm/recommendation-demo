package com.yfzm.recommendation.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TweetCollocEntityPK implements Serializable {
    private int tweetId;
    private int collocationId;

    @Column(name = "tweet_id", nullable = false)
    @Id
    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    @Column(name = "collocation_id", nullable = false)
    @Id
    public int getCollocationId() {
        return collocationId;
    }

    public void setCollocationId(int collocationId) {
        this.collocationId = collocationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetCollocEntityPK that = (TweetCollocEntityPK) o;
        return tweetId == that.tweetId &&
                collocationId == that.collocationId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tweetId, collocationId);
    }
}
