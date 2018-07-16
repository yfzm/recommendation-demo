package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "user", schema = "mashion", catalog = "")
public class UserEntity {
    private int userId;
    private String username;


    private String password;
    private String phone;


    private RoleEntity roleEntity;
    private Set<UserCollocEntity> collocAssoc;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone", length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @OneToMany(mappedBy = "collocation")
    public Set<UserCollocEntity> getCollocAssoc() {
        return collocAssoc;
    }

    public void setCollocAssoc(Set<UserCollocEntity> collocAssoc) {
        this.collocAssoc = collocAssoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity entity = (UserEntity) o;
        return userId == entity.userId &&
                Objects.equals(username, entity.username) &&
                Objects.equals(password, entity.password) &&
                Objects.equals(phone, entity.phone) &&
                Objects.equals(roleEntity, entity.roleEntity) &&
                Objects.equals(collocAssoc, entity.collocAssoc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, username, password, phone, roleEntity, collocAssoc);
    }
}
