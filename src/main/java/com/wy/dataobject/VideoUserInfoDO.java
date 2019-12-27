package com.wy.dataobject;

import java.util.Date;

public class VideoUserInfoDO {
    private Long id;

    private String nickName;

    private String faceImage;

    private Integer fansCount;

    private Integer followCount;

    private Integer reveiveLikeCount;

    private Date createTime;

    private Date updateTime;

    private Long memberId;

    private String phone;

    public VideoUserInfoDO(Long id, String nickName, String faceImage, Integer fansCount, Integer followCount, Integer reveiveLikeCount, Date createTime, Date updateTime, Long memberId, String phone) {
        this.id = id;
        this.nickName = nickName;
        this.faceImage = faceImage;
        this.fansCount = fansCount;
        this.followCount = followCount;
        this.reveiveLikeCount = reveiveLikeCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.memberId = memberId;
        this.phone = phone;
    }

    public VideoUserInfoDO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage == null ? null : faceImage.trim();
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public Integer getReveiveLikeCount() {
        return reveiveLikeCount;
    }

    public void setReveiveLikeCount(Integer reveiveLikeCount) {
        this.reveiveLikeCount = reveiveLikeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}