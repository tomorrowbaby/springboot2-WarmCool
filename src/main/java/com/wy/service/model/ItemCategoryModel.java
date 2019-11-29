package com.wy.service.model;

import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 描述：商品种类模型
 * @author wangyu
 * @date 2019/8/16
 */

public class ItemCategoryModel implements Serializable {

    private Long id;

    private Long parentId;  //父节点

    private String name;   //分类名称

    private Integer status;

    private Integer sortOrder;  //排列序号

    private Boolean isParent;   //是否为父节点

    private String icon; //图标

    private String remark;  //备注

    private DateTime created;

    private DateTime updated;

    private String returnResult;

    public String getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }
}
