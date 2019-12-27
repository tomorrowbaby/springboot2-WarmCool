package com.wy.service;

import com.wy.common.response.CommonReturnPageInfo;
import com.wy.service.model.OrderModel;

import java.util.List;

/**
 * 描述：订单服务接口
 * @author wangyu
 * @date 2019/9/20
 */


public interface OrderService {


    /**
     * 描述：分页条件查询排序
     * @param orderId
     * @param start
     * @param length
     * @param searchKey
     * @param sortColumn
     * @param sortBy
     * @param minDate
     * @param maxDate
     * @return
     */
    CommonReturnPageInfo listOrders(String orderId,Integer start, Integer length,
                                    String searchKey, String sortColumn,
                                    String sortBy, String minDate, String maxDate);





}
