package com.pelime.xtools.sysmodel;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name="SysRolePermission",inverseJoinColumns =
            {@JoinColumn(name="permissionId")},joinColumns =
            {@JoinColumn(name="roleId")})
    private Set<SysPermission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
