package com.wy.service;

import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.service.model.OrderModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：订单服务接口
 * @author wangyu
 * @date 2019/9/20
 */


public interface OrderService {


    /**
     * 描述：分页条件查询排序
     * @param start
     * @param length
     * @param searchKey
     * @param sortBy
     * @param sortColumn
     * @return
     */
    CommonReturnPageInfo getListOrderByPage(Integer start, Integer length,
                                    String searchKey,String sortColumn,String sortBy) throws BusinessException;


    /**
     * 描述：查询询订单总数
     * @return
     */
    Long selectAllOrderCount() throws BusinessException;

    /**
     * 描述：订单发货
     * @param orderId
     * @param shippingName
     * @param shippingCode
     * @param postFee
     * @return
     * @throws BusinessException
     */
    OrderModel orderDeliver(String orderId, String shippingName, String shippingCode, BigDecimal postFee) throws BusinessException;

    /**
     * 描述 ： 设置订单备注
     * @param orderId
     * @param remarkMessage
     * @return
     */
    OrderModel setOrderRemarks(String orderId,String remarkMessage) throws BusinessException;


    /**
     * 描述：关闭订单
     * @param orderId
     * @return
     */
    OrderModel closeOrder(String orderId) throws BusinessException;


    /**
     * 描述：删除订单信息
     * @param orderId
     * @return
     */
    OrderModel deleteOrder(String orderId) throws BusinessException;
}
