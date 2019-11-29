package com.wy.dao;

import com.github.pagehelper.Page;
import com.wy.dataobject.ItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);


    //根据状态查询数量
    Long selectItemCount(@Param("state") Integer state);

    Page<ItemDO> selectPageItemByItemInfo(@Param("cid") Long cid,
                                          @Param("searchKey") String searchKey,
                                          @Param("minDate") String minDate,
                                          @Param("maxDate") String maxDate,
                                          @Param("orderCol") String orderCol,
                                          @Param("orderDir") String orderDir);
}