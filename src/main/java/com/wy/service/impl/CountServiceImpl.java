package com.wy.service.impl;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.wy.common.constant.CountConstant;
import com.wy.dao.OrderDOMapper;
import com.wy.dataobject.OrderChartDataDO;
import com.wy.service.CountService;
import com.wy.service.model.OrderChartDataModel;
import com.wy.utils.TimeUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Exrickx
 * @from xmall
 */
@Service
public class CountServiceImpl implements CountService {



    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    public List<OrderChartDataModel> getOrderCountData(int type, Date startTime, Date endTime, int year) {

        List<OrderChartDataDO> fullData = new ArrayList<>();
        //作者就是不想用case
        if(type == CountConstant.THIS_WEEK){
            //本周
            List<OrderChartDataDO> data = orderDOMapper.selectOrderChart(TimeUtil.getBeginDayOfWeek(),TimeUtil.getEndDayOfWeek());
            fullData = getFullData(data, TimeUtil.getBeginDayOfWeek(),TimeUtil.getEndDayOfWeek());
        }else if(type == CountConstant.THIS_MONTH){
            //本月
            List<OrderChartDataDO> data = orderDOMapper.selectOrderChart(TimeUtil.getBeginDayOfMonth(),TimeUtil.getEndDayOfMonth());
            fullData = getFullData(data,TimeUtil.getBeginDayOfMonth(),TimeUtil.getEndDayOfMonth());
        }else if(type == CountConstant.LAST_MONTH){
            //上个月
            List<OrderChartDataDO> data = orderDOMapper.selectOrderChart(TimeUtil.getBeginDayOfLastMonth(), TimeUtil.getEndDayOfLastMonth());
            fullData = getFullData(data,TimeUtil.getBeginDayOfLastMonth(),TimeUtil.getEndDayOfLastMonth());
        }else if(type == CountConstant.CUSTOM_DATE){
            //自定义
            List<OrderChartDataDO> data = orderDOMapper.selectOrderChart(startTime, endTime);
            fullData = getFullData(data,startTime, endTime);
        }else if(type == CountConstant.CUSTOM_YEAR){
            List<OrderChartDataDO> data = orderDOMapper.selectOrderChartByYear(year);
            fullData = getFullYearData(data,year);
        }

        List<OrderChartDataModel> orderChartDataModels = new ArrayList<>();

        for (OrderChartDataDO order:
             fullData) {
            OrderChartDataModel orderChartDataModel = new OrderChartDataModel();
            BeanUtils.copyProperties(order,orderChartDataModel);
            orderChartDataModels.add(orderChartDataModel);
        }

        return orderChartDataModels;
    }

    /**
     * 无数据补0
     * @param startTime
     * @param endTime
     */
    public List<OrderChartDataDO> getFullData(List<OrderChartDataDO> data,Date startTime, Date endTime){

        List<OrderChartDataDO> fullData = new ArrayList<>();
        //相差
        long betweenDay = DateUtil.between(startTime, endTime, DateUnit.DAY);
        //起始时间
        Date everyday = startTime;
        int count = -1;
        for(int i=0;i<=betweenDay;i++){
            boolean flag = true;
            for(OrderChartDataDO chartData:data){
                if(DateUtils.isSameDay(chartData.getTime(),everyday)){
                    //有数据
                    flag = false;
                    count++;
                    break;
                }
            }
            if(!flag){
                fullData.add(data.get(count));
            }else{
                OrderChartDataDO orderChartData = new OrderChartDataDO();
                orderChartData.setTime(everyday);
                orderChartData.setMoney(new BigDecimal("0"));
                fullData.add(orderChartData);
            }

            //时间+1天
            Calendar cal = Calendar.getInstance();
            cal.setTime(everyday);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            everyday = cal.getTime();
        }
        return fullData;
    }

    /**
     * 无数据补0
     * @param data
     * @param year
     * @return
     */
    public List<OrderChartDataDO> getFullYearData(List<OrderChartDataDO> data,int year){

        List<OrderChartDataDO> fullData = new ArrayList<>();
        //起始月份
        Date everyMonth = TimeUtil.getBeginDayOfYear(year);
        int count = -1;
        for(int i=0;i<12;i++){
            boolean flag = true;
            for(OrderChartDataDO chartData:data){
                if(DateUtil.month(chartData.getTime())==DateUtil.month(everyMonth)){
                    //有数据
                    flag = false;
                    count++;
                    break;
                }
            }
            if(!flag){
                fullData.add(data.get(count));
            }else{
                OrderChartDataDO orderChartData = new OrderChartDataDO();
                orderChartData.setTime(everyMonth);
                orderChartData.setMoney(new BigDecimal("0"));
                fullData.add(orderChartData);
            }

            //时间+1天
            Calendar cal = Calendar.getInstance();
            cal.setTime(everyMonth);
            cal.add(Calendar.MONTH, 1);
            everyMonth = cal.getTime();
        }
        return fullData;
    }
}
