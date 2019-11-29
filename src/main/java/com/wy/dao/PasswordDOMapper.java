package com.wy.dao;

import com.wy.dataobject.PasswordDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PasswordDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PasswordDO record);

    int insertSelective(PasswordDO record);

    PasswordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PasswordDO record);

    int updateByPrimaryKey(PasswordDO record);
}