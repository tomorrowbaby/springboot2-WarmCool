package com.wy.dao;

import com.wy.dataobject.PanelDO;
import com.wy.dataobject.PanelDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface PanelDOMapper {
    long countByExample(PanelDOExample example);

    int deleteByExample(PanelDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PanelDO record);

    int insertSelective(PanelDO record);

    List<PanelDO> selectByExample(PanelDOExample example);

    PanelDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PanelDO record, @Param("example") PanelDOExample example);

    int updateByExample(@Param("record") PanelDO record, @Param("example") PanelDOExample example);

    int updateByPrimaryKeySelective(PanelDO record);

    int updateByPrimaryKey(PanelDO record);
}