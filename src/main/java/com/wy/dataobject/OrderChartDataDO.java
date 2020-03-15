package com.wy.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：订单销量统计
 * @author wangyu
 * @date 2020/3/10
 */
public class OrderChartDataDO implements Serializable {

    Date time;

    BigDecimal money;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
