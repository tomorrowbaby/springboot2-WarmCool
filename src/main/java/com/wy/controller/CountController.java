package com.wy.controller;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.wy.common.constant.CountConstant;
import com.wy.common.response.CommonReturnType;
import com.wy.controller.viewobject.ChartDataVO;
import com.wy.service.CountService;
import com.wy.service.model.OrderChartDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Exrickx
 */
@RestController
@Api("统计")
public class CountController {

    @Autowired
    private CountService countService;

    @RequestMapping(value = "/count/order",method = RequestMethod.GET)
    @ApiOperation(value = "通过panelId获得板块内容列表")
    public CommonReturnType countOrder(@RequestParam int type,
                                     @RequestParam(required = false) String startTime,
                                     @RequestParam(required = false) String endTime,
                                     @RequestParam(required = false) int year){

        ChartDataVO data = new ChartDataVO();
        Date startDate = null, endDate = null;
        if(type == CountConstant.CUSTOM_DATE){
            startDate = DateUtil.beginOfDay(DateUtil.parse(startTime));
            endDate = DateUtil.endOfDay(DateUtil.parse(endTime));
            long betweenDay = DateUtil.between(startDate, endDate, DateUnit.DAY);
            if(betweenDay>31){
                return CommonReturnType.create("所选日期过长","fail");
            }
        }
        List<OrderChartDataModel> list = countService.getOrderCountData(type, startDate, endDate, year);
        List<Object> xDatas = new ArrayList<>();
        List<Object> yDatas = new ArrayList<>();
        BigDecimal countAll = new BigDecimal("0");
        for(OrderChartDataModel orderData : list){
            if(type==CountConstant.CUSTOM_YEAR){
                xDatas.add(DateUtil.format(orderData.getTime(),"yyyy-MM"));
            }else{
                xDatas.add(DateUtil.formatDate(orderData.getTime()));
            }
            yDatas.add(orderData.getMoney());
            countAll=countAll.add(orderData.getMoney());
        }
        data.setxDatas(xDatas);
        data.setyDatas(yDatas);
        data.setCountAll(countAll);
        return CommonReturnType.createData(data);
    }
}
