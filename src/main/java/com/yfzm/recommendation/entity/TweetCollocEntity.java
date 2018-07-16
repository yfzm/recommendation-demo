package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tweet_colloc", schema = "recommendation", catalog = "")
@IdClass(TweetCollocEntityPK.class)
public class TweetCollocEntity {
    private int tweetId;
    private int collocationId;

    @Id
    @Column(name = "tweet_id", nullable = false)
    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    @Id
    @Column(name = "collocation_id", nullable = false)
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
        TweetCollocEntity that = (TweetCollocEntity) o;
        return tweetId == that.tweetId &&
                collocationId == that.collocationId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tweetId, collocationId);
    }
}
