package com.wy.dao;

import com.wy.dataobject.RolePermDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermDO record);

    int insertSelective(RolePermDO record);

    RolePermDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermDO record);

    int updateByPrimaryKey(RolePermDO record);

    List<Integer> selectByRoleId(@Param("roleId")Integer roleId);

    List<Integer> selectByPermissionId(@Param("permissionId")Integer permissionId);

}