package com.wy.dao;

import com.wy.dataobject.DictDO;
import com.wy.dataobject.DictDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DictDOMapper {
    long countByExample(DictDOExample example);

    int deleteByExample(DictDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DictDO record);

    int insertSelective(DictDO record);

    List<DictDO> selectByExample(DictDOExample example);

    DictDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DictDO record, @Param("example") DictDOExample example);

    int updateByExample(@Param("record") DictDO record, @Param("example") DictDOExample example);

    int updateByPrimaryKeySelective(DictDO record);

    int updateByPrimaryKey(DictDO record);
}