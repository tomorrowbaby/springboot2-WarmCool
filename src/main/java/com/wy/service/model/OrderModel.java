package com.wy.service.model;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：订单模型
 * @author wangyu
 * @date 2019/9/20
 */


public class OrderModel implements Serializable {


    private String orderId;

    /**
     * 支付金额
     */
    private BigDecimal payment;

    /**
     * 运费
     */
    private BigDecimal postFee;

    /**
     * 支付方式
     */
    private Integer paymentType;

    /**
     * 支付时间
     */
    private DateTime paymentTime;

    private DateTime updateTime;

    private DateTime createTime;


    /**
     * 运送时间
     */
    private DateTime consignTime;

    /**
     * 订单状态：0：待支付，1：已完成
     */
    private Integer status;

    /**
     * 库存信息
     */
    private Long stock;

    /**
     * 数量
     */
    private Long num;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 订单开始时间
     */
    private DateTime startTime;

    /**
     * 订单自动结束时间
     */
    private DateTime endTime;

    /**
     *订单关闭时间
     */
    private DateTime closeTime;

    /**
     * 快递名
     */
    private String shippingName;

    /**
     * 货号
     */
    private String shippingCode;


    private Long userId;


    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家打星
     */
    private String buyerNick;

    /**
     * 买家评论
     */
    private Boolean buyerComment;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getPostFee() {
        return postFee;
    }

    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public DateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(DateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public DateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(DateTime updateTime) {
        this.updateTime = updateTime;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public DateTime getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(DateTime consignTime) {
        this.consignTime = consignTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public DateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(DateTime closeTime) {
        this.closeTime = closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public Boolean getBuyerComment() {
        return buyerComment;
    }

    public void setBuyerComment(Boolean buyerComment) {
        this.buyerComment = buyerComment;
    }
}
