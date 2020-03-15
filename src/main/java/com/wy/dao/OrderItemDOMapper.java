package com.wy.dao;

import com.wy.dataobject.OrderItemDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderItemDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderItemDO record);

    int insertSelective(OrderItemDO record);

    OrderItemDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderItemDO record);

    int updateByPrimaryKey(OrderItemDO record);

    int deleteByOrderId(String orderId);
}