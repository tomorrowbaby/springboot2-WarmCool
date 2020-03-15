package com.wy.dao;

import com.wy.dataobject.RolePermDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermDO record);

    int insertSelective(RolePermDO record);

    RolePermDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermDO record);

    int updateByPrimaryKey(RolePermDO record);
}