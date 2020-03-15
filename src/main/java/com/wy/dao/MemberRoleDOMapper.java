package com.wy.dao;

import com.wy.dataobject.MemberRoleDO;
import com.wy.dataobject.MemberRoleDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MemberRoleDOMapper {
    long countByExample(MemberRoleDOExample example);

    int deleteByExample(MemberRoleDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberRoleDO record);

    int insertSelective(MemberRoleDO record);

    List<MemberRoleDO> selectByExample(MemberRoleDOExample example);

    MemberRoleDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberRoleDO record, @Param("example") MemberRoleDOExample example);

    int updateByExample(@Param("record") MemberRoleDO record, @Param("example") MemberRoleDOExample example);

    int updateByPrimaryKeySelective(MemberRoleDO record);

    int updateByPrimaryKey(MemberRoleDO record);
}