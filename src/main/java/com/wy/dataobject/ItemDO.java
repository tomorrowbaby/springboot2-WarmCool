package com.wy.dataobject;

import java.math.BigDecimal;
import java.util.Date;

public class ItemDO {
    private Long id;

    private String title;

    private String sellPoint;

    private BigDecimal price;

    private String image;

    private Long cid;

    private Integer status;

    private Date created;

    private Date updated;

    public ItemDO(Long id, String title, String sellPoint, BigDecimal price, String image, Long cid, Integer status, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.sellPoint = sellPoint;
        this.price = price;
        this.image = image;
        this.cid = cid;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public ItemDO() {
        super();
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
        this.title = title == null ? null : title.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
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
        this.image = image == null ? null : image.trim();
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}