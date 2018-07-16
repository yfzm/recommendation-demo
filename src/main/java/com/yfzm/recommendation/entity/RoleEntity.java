package com.yfzm.recommendation.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role", schema = "recommendation", catalog = "")
public class RoleEntity {
    private int roleId;
    private String roleDescription;

    @Id
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_description", nullable = true, length = 10)
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleId == that.roleId &&
                Objects.equals(roleDescription, that.roleDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, roleDescription);
    }
}
