package com.wy.dataobject;

public class PermissionDO {
    private Integer id;

    private String name;

    private String permission;

    public PermissionDO(Integer id, String name, String permission) {
        this.id = id;
        this.name = name;
        this.permission = permission;
    }

    public PermissionDO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}