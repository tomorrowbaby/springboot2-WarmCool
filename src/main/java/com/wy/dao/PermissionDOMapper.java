package com.wy.dao;

import com.wy.dataobject.PermissionDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionDO record);

    int insertSelective(PermissionDO record);

    PermissionDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionDO record);

    int updateByPrimaryKey(PermissionDO record);
}