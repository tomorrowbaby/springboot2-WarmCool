package com.wy.dao;

import com.wy.dataobject.ExpressDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpressDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpressDO record);

    int insertSelective(ExpressDO record);

    ExpressDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExpressDO record);

    int updateByPrimaryKey(ExpressDO record);

    /**
     * 描述：获取快递列表
     * @param sortBy
     * @return
     */
    List<ExpressDO> selectExpressList(@Param("sortBy") String sortBy);



}