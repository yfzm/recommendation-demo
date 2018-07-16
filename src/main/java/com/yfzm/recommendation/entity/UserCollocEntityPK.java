package com.yfzm.recommendation.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCollocEntityPK implements Serializable {
    private int user;
    private int collocation;

    //    @Column(name = "user_id")
    //    @Id
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    //    @Column(name = "collocation_id")
    //    @Id
    public int getCollocation() {
        return collocation;
    }

    public void setCollocation(int collocation) {
        this.collocation = collocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCollocEntityPK that = (UserCollocEntityPK) o;
        return user == that.user && collocation == that.collocation;
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, collocation);
    }
}
