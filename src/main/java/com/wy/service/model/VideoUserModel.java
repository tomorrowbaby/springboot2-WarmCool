package com.wy.service.model;

import org.joda.time.DateTime;

import java.io.Serializable;

public class VideoUserModel implements Serializable {


    private Long id;

    //昵称
    private String nickName;

    //头像
    private String faceImage;

    //粉丝数量
    private Integer fansCount;

    //关注数
    private Integer followCount;

    //点赞数
    private Integer reveiveLikeCount;

    private DateTime createTime;

    private DateTime updateTime;

    private String returnResult;

    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    //账户绑定id
    private Long memberId;

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
        this.nickName = nickName;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
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

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public DateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(DateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }


}
