package com.wy.service;


import com.wy.service.model.OrderChartDataModel;

import java.util.Date;
import java.util.List;

/**
 * 描述：订单销量接口
 * @author wangyu
 * @date 2020/3/10
 */
public interface CountService {

    /**
     * 统计订单销量
     * @param type
     * @param startTime
     * @param endTime
     * @param year
     * @return
     */
    List<OrderChartDataModel> getOrderCountData(int type, Date startTime, Date endTime, int year);
}
