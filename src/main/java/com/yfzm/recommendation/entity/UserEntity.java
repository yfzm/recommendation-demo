package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "user", schema = "recommendation", catalog = "")
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


    @OneToMany(mappedBy = "collocation", fetch = FetchType.EAGER)
    public Set<UserCollocEntity> getCollocAssoc() {
        return collocAssoc;
    }

    public void setCollocAssoc(Set<UserCollocEntity> collocAssoc) {
        this.collocAssoc = collocAssoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(roleEntity, that.roleEntity) &&
                Objects.equals(collocAssoc, that.collocAssoc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, username, password, phone, roleEntity, collocAssoc);
    }
}
