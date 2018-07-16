package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tweet", schema = "mashion", catalog = "")
public class TweetEntity {
    private int tweetId;
    private Integer userId;
    private String title;
    private String description;
    private String detail;
    private Integer likeCount;
    private Integer commentCount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id", nullable = false)
    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "title", nullable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "detail", nullable = true)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "like_count", nullable = true)
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "comment_count", nullable = true)
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetEntity that = (TweetEntity) o;
        return tweetId == that.tweetId &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(detail, that.detail) &&
                Objects.equals(likeCount, that.likeCount) &&
                Objects.equals(commentCount, that.commentCount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tweetId, userId, title, description, detail, likeCount, commentCount);
    }
}
