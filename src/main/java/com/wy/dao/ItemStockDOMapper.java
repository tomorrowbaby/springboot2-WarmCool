package com.wy.dao;

import com.wy.dataobject.ItemStockDO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ItemStockDOMapper {
    ItemStockDO selectByItemId(Long itemId);

    int deleteByItemId(Long itemId);

    int deleteByPrimaryKey(Long id);

    int insert(ItemStockDO record);

    int insertSelective(ItemStockDO record);

    ItemStockDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemStockDO record);

    int updateByPrimaryKey(ItemStockDO record);
}