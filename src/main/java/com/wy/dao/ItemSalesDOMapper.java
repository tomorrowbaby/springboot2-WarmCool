package com.wy.dao;

import com.wy.dataobject.ItemSalesDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemSalesDOMapper {

    int deleteByItemId(Long itemId);

    int deleteByPrimaryKey(Long id);

    int insert(ItemSalesDO record);

    int insertSelective(ItemSalesDO record);

    ItemSalesDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemSalesDO record);

    int updateByPrimaryKey(ItemSalesDO record);
}