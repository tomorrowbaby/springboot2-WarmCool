package com.wy.dataobject;

public class MemberRoleDO {
    private Integer id;

    private Long memberId;

    private Integer roleId;

    public MemberRoleDO(Integer id, Long memberId, Integer roleId) {
        this.id = id;
        this.memberId = memberId;
        this.roleId = roleId;
    }

    public MemberRoleDO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}