package com.wy.dao;

import com.wy.dataobject.ItemCategoryDO;
import com.wy.dataobject.ItemCategoryDOExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ItemCategoryDOMapper {


    int deleteByPrimaryKey(Long id);

    int insert(ItemCategoryDO record);

    int insertSelective(ItemCategoryDO record);

    List<ItemCategoryDO> selectByExample(ItemCategoryDOExample example);

    ItemCategoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemCategoryDO record);

    int updateByPrimaryKey(ItemCategoryDO record);
}