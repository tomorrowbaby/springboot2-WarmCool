package com.wy.service.model;

import org.joda.time.DateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：会员模型
 * @author wangyu
 * @date 2019/8/4
 */

public class MemberModel implements Serializable {

    private Long id;

    @NotNull(message = "用户名为必填项")
    private String username;

    @NotNull(message = "用户性别为必填项")
    private String sex;

    @NotNull(message = "用户手机号码为必填项")
    private String phone;

    @NotNull(message = "用户邮箱为必填项目")
    private String email;

    private DateTime create; //创建日期

    private DateTime update; //最后更新时间

    private String address; //住址

    private String province; //省会

    private String city;    //城市

    private String district; //地区

    private Integer state; //会员状态

    private String file; //头像

    private String description;

    private String points;  //积分

    private BigDecimal balance;  //余额

    private String password;

    private String returnResult ;


    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getCreate() {
        return create;
    }

    public void setCreate(DateTime create) {
        this.create = create;
    }

    public DateTime getUpdate() {
        return update;
    }

    public void setUpdate(DateTime update) {
        this.update = update;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
