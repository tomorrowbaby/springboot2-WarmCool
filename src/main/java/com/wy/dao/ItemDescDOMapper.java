package com.wy.dao;

import com.wy.dataobject.ItemDescDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemDescDOMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(ItemDescDO record);

    int insertSelective(ItemDescDO record);

    ItemDescDO selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(ItemDescDO record);

    int updateByPrimaryKeyWithBLOBs(ItemDescDO record);

    int updateByPrimaryKey(ItemDescDO record);
}