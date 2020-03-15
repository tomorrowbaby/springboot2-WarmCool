package com.wy.dao;

import com.github.pagehelper.Page;
import com.wy.dataobject.ItemDO;
import com.wy.dataobject.es.SearchItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


    List<ItemDO> selectItemByCondition(@Param("cid") int cid, @Param("search") String search,
                                       @Param("orderCol") String orderCol, @Param("orderDir") String orderDir);

    List<ItemDO> selectItemByMultiCondition(@Param("cid") int cid, @Param("search") String search, @Param("minDate") String minDate,
                                            @Param("maxDate") String maxDate, @Param("orderCol") String orderCol,
                                            @Param("orderDir") String orderDir);

    List<ItemDO> selectItemFront(@Param("cid") Long cid,
                                 @Param("orderCol") String orderCol, @Param("orderDir") String orderDir,
                                 @Param("priceGt") int priceGt, @Param("priceLte") int priceLte);

    List<SearchItemDO> getItemList();

    SearchItemDO getItemById(@Param("id") Long id);

}