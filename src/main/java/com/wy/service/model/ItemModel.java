package com.wy.service.model;

import org.joda.time.DateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：商品模型
 * @author wangyu
 * @date 2019/8/16
 */

public class ItemModel implements Serializable {

    private Long id;

    @Max(value = 10,message = "用户名不能高于10个字符")
    @Min(value = 2,message = "用户名不能低于2个字符")
    private String title;

    private String sellPoint; //卖点

    @NotNull(message = "价格必须填写")
    private BigDecimal price;

    private String image;

    @NotNull(message = "商品分类必须填写")
    private Long cid;  //商品分类

    private Integer status;

    private DateTime created;

    private DateTime updated;

    private Integer sales;   //销量

    @NotNull(message = "库存必须填写")
    @Min(value = 1,message = "库存不能低于1")
    @Max(value = 9999999,message = "库存不能等于高于10000000")
    private Long stock;     //库存

    @NotNull(message = "商品详情必须填写")
    private String description;

    private Long count;

    public Integer num;

    private String returnResult;

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    public Long getCount() {
        return count;
    }



    public Integer getNum() {
        return num;
    }

    public void setNum(Integer nun) {
        this.num = num;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
