package com.wy.dao;

import com.wy.dataobject.PanelContentDO;
import com.wy.dataobject.PanelContentDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface PanelContentDOMapper {
    long countByExample(PanelContentDOExample example);

    int deleteByExample(PanelContentDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PanelContentDO record);

    int insertSelective(PanelContentDO record);

    List<PanelContentDO> selectByExample(PanelContentDOExample example);

    PanelContentDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PanelContentDO record, @Param("example") PanelContentDOExample example);

    int updateByExample(@Param("record") PanelContentDO record, @Param("example") PanelContentDOExample example);

    int updateByPrimaryKeySelective(PanelContentDO record);

    int updateByPrimaryKey(PanelContentDO record);
}