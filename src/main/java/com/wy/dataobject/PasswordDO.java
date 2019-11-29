package com.wy.dataobject;

public class PasswordDO {
    private Long id;

    private String encrptPassword;

    private Long userId;

    private Long memberId;

    public PasswordDO(Long id, String encrptPassword, Long userId, Long memberId) {
        this.id = id;
        this.encrptPassword = encrptPassword;
        this.userId = userId;
        this.memberId = memberId;
    }

    public PasswordDO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword == null ? null : encrptPassword.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}