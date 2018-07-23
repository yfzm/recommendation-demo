package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "record")
public class RecordEntity {

    private Long recordId;

    private UserEntity user;

    private CollocationEntity collocation;

    private Double preferenceValue;

    private Timestamp visitTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false)
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "collocation_id")
    public CollocationEntity getCollocation() {
        return collocation;
    }

    public void setCollocation(CollocationEntity collocation) {
        this.collocation = collocation;
    }

    @Column(name = "preference_value", nullable = false)
    public Double getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(Double preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

    @Column(name = "visit_time", nullable = false)
    public Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp timestamp) {
        this.visitTime = timestamp;
    }
}
