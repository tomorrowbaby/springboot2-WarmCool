package com.wy.dataobject.front;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：前台所有商品返回
 * @author wangyu
 * @date 2020/3/18
 */
public class AllGoodsResultDO implements Serializable {

    private int total;

    private List<?> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
