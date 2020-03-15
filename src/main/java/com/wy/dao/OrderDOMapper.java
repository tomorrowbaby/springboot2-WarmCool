package com.wy.dao;

import com.github.pagehelper.Page;
import com.wy.dataobject.OrderChartDataDO;
import com.wy.dataobject.OrderDO;
import com.wy.service.model.OrderChartDataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderDOMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);


    /**
     * 描述：分页模糊查询订单
     * @param searchKey
     * @return
     */
    Page<OrderDO> selectListByPage(@Param("searchKey") String searchKey,
                                   @Param("sortColumn") String sortColumn,
                                   @Param("sortBy") String sortBy);

    /**
     * 查询订单记录数
     * @return
     */
    Long selectCount();

    List<OrderChartDataDO> selectOrderChart(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<OrderChartDataDO> selectOrderChartByYear(@Param("year") int year);

}