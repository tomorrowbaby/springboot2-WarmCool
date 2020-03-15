package com.wy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.common.status.EmStatus;
import com.wy.dao.OrderDOMapper;
import com.wy.dao.OrderItemDOMapper;
import com.wy.dao.OrderShippingDOMapper;
import com.wy.dataobject.MemberDO;
import com.wy.dataobject.OrderDO;
import com.wy.service.OrderService;
import com.wy.service.model.OrderModel;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private OrderItemDOMapper orderItemDOMapper;

    @Autowired
    private OrderShippingDOMapper orderShippingDOMapper;

    @Override
    public CommonReturnPageInfo getListOrderByPage(Integer start, Integer length, String searchKey,String sortColumn,String sortBy) throws BusinessException {

        if (start == null || length == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }


        /**
         * 开始分页的起始数
         * 分页长度
         */
        PageHelper.startPage(start/length+1,length);
        Page<OrderDO> orderDOPage = new Page<>();
        try {
            orderDOPage = orderDOMapper.selectListByPage(searchKey,sortColumn,sortBy);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        //分页结果获取
        List<OrderDO> orderDOList = orderDOPage.getResult();

        CommonReturnPageInfo pageInfoModel = new CommonReturnPageInfo();
        pageInfoModel.setData(orderDOList);
        pageInfoModel.setRecordsTotal(Long.bitCount(this.selectAllOrderCount()));
        pageInfoModel.setRecordsFiltered((int)(orderDOPage.getTotal()));

        return pageInfoModel;
    }

    @Override
    public Long selectAllOrderCount() throws BusinessException {
        long count = 0;
        try{
            count = orderDOMapper.selectCount();
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        return count;
    }

    @Override
    @Transactional
    public OrderModel orderDeliver(String orderId, String shippingName, String shippingCode, BigDecimal postFee) throws BusinessException {

        //1.参数校验
        if (orderId == null ) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //2.设置
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderId(orderId);
        orderDO.setShippingName(shippingName);
        orderDO.setShippingCode(shippingCode);
        orderDO.setPostFee(postFee);
        orderDO.setStatus(EmStatus.ORDER_DEAL.getStatusCode());
        int row = 0;
        try {
            row  = orderDOMapper.updateByPrimaryKeySelective(orderDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        OrderModel orderModel = new OrderModel();

        if (row > 0) {
            orderModel.setReturnResult("success");
        }else {
            orderModel.setReturnResult("不存在该订单！");
        }

        return orderModel;
    }

    @Override
    @Transactional
    public OrderModel setOrderRemarks(String orderId, String remarkMessage) throws BusinessException {
        if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(remarkMessage)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //2.设置
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderId(orderId);
        orderDO.setBuyerMessage(remarkMessage);
        int row = 0;
        try {
            row  = orderDOMapper.updateByPrimaryKeySelective(orderDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        OrderModel orderModel = new OrderModel();

        if (row > 0) {
            orderModel.setReturnResult("success");
        }else {
            orderModel.setReturnResult("不存在该订单！");
        }

        return orderModel;
    }

    @Override
    @Transactional
    public OrderModel closeOrder(String orderId) throws BusinessException {

        if (orderId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        OrderDO orderDO = new OrderDO();

        //设置关闭订单的状态
        orderDO.setStatus(EmStatus.ORDER_CLOSE.getStatusCode());
        orderDO.setOrderId(orderId);
        orderDO.setCloseTime(new Date());
        orderDO.setUpdateTime(new Date());

        int row = 0;
        try {
            row  = orderDOMapper.updateByPrimaryKeySelective(orderDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        OrderModel orderModel = new OrderModel();

        if (row > 0) {
            orderModel.setReturnResult("success");
        }else {
            orderModel.setReturnResult("不存在该订单！");
        }
        return orderModel;
    }

    @Override
    @Transactional
    public OrderModel deleteOrder(String orderId) throws BusinessException {

        if (orderId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        int row = 0;

        try{
            row +=orderDOMapper.deleteByPrimaryKey(orderId);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.ORDER_DELETE_ERROR);
        }
        try{
            row += orderShippingDOMapper.deleteByPrimaryKey(orderId);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.ORDER_SHIPPING_DELETE_ERROR);
        }

        try{
            row += orderItemDOMapper.deleteByOrderId(orderId);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.ORDER_ITEM_DELETE_ERROR);
        }

        OrderModel orderModel = new OrderModel();

        if (row == 3) {
            orderModel.setReturnResult("success");
        }else {
            orderModel.setReturnResult("订单删除失败！");
        }
        return orderModel;
    }
}
