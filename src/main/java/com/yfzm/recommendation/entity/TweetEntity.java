package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tweet", schema = "recommendation", catalog = "")
public class TweetEntity {
    private int tweetId;
    private UserEntity user;
    private String title;
    private String description;
    private String detail;
    private Timestamp createTime;
    private Integer likeCount;
    private Integer commentCount;
    private Set<CollocationEntity> collocations;
    private Set<CommentEntity> comments;
    private Set<UserEntity> starUsers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id", nullable = false)
    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "detail", nullable = false)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "like_count", nullable = false)
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "comment_count", nullable = false)
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "tweet_colloc", joinColumns = {@JoinColumn(name = "tweet_id")},
            inverseJoinColumns = {@JoinColumn(name = "collocation_id")})
    public Set<CollocationEntity> getCollocations() {
        return collocations;
    }

    public void setCollocations(Set<CollocationEntity> collocations) {
        this.collocations = collocations;
    }

    @OneToMany(mappedBy = "tweet", cascade = {CascadeType.ALL})
    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "star", joinColumns = {@JoinColumn(name = "tweet_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public Set<UserEntity> getStarUsers() {
        return starUsers;
    }

    public void setStarUsers(Set<UserEntity> starUsers) {
        this.starUsers = starUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetEntity that = (TweetEntity) o;
        return tweetId == that.tweetId &&
                Objects.equals(user, that.user) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(detail, that.detail) &&
                Objects.equals(likeCount, that.likeCount) &&
                Objects.equals(commentCount, that.commentCount) &&
                Objects.equals(collocations, that.collocations) &&
                Objects.equals(comments, that.comments) &&
                Objects.equals(starUsers, that.starUsers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tweetId, user, title, description, detail, likeCount, commentCount, collocations, comments, starUsers);
    }
}
