package com.wy.dao;

import com.wy.dataobject.OrderShippingDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderShippingDOMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderShippingDO record);

    int insertSelective(OrderShippingDO record);

    OrderShippingDO selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderShippingDO record);

    int updateByPrimaryKey(OrderShippingDO record);
}