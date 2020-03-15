package com.wy.controller;


import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.common.response.CommonReturnType;
import com.wy.service.OrderService;
import com.wy.service.model.OrderModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 描述：后台订单控制层
 */
@Api("后台订单接口")
@RestController
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    @ApiOperation("查询订单列表")
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public CommonReturnPageInfo getOrderList(Integer draw, Integer start, Integer length,@RequestParam("search[value]") String searchKey,
                                             @RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir) throws BusinessException {

        if (start == null || length == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //获取客户端需要排序的列
        String[] columns = {"checkbox","order_id", "payment","buyer_nick", "shipping_code","buyer_message", "create_time", "payment_time", "close_time","end_time"};
        String sortColumn = columns[orderCol];
        //默认排序列
        if(sortColumn == null) {
            sortColumn = "create_time";
        }

        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }

        CommonReturnPageInfo commonReturnPageInfo = null;
        commonReturnPageInfo = orderService.getListOrderByPage(start,length,searchKey,sortColumn,orderDir);

        return commonReturnPageInfo;
    }


    @ApiOperation("获取订单总数")
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public CommonReturnType getOrderCount() throws BusinessException {
        return CommonReturnType.create(orderService.selectAllOrderCount());
    }


    @ApiOperation("订单发货")
    @RequestMapping(value = "/deliver",method = RequestMethod.POST)
    public CommonReturnType orderDeliver(@RequestParam String orderId,
                                         @RequestParam String shippingName,
                                         @RequestParam String shippingCode,
                                         @RequestParam BigDecimal postFee) throws BusinessException {


        OrderModel orderModel = orderService.orderDeliver(orderId,shippingName,shippingCode,postFee) ;
        if (orderModel.getReturnResult() == "success") {
            return CommonReturnType.create(null);
        }else {
            return CommonReturnType.create(orderModel.getReturnResult(),"fail");
        }
    }


    @ApiOperation("备注信息")
    @RequestMapping(value = "/remark",method = RequestMethod.POST)
    public CommonReturnType remark(@RequestParam("orderId") String orderId,@RequestParam("message")String message) throws BusinessException {

        OrderModel orderModel = orderService.setOrderRemarks(orderId,message);
        if (orderModel.getReturnResult() == "success") {
            return CommonReturnType.create(null);
        }else {
            return CommonReturnType.create(orderModel.getReturnResult(),"fail");
        }
    }


    @ApiOperation("关闭订单")
    @RequestMapping(value = "/close/{orderId}",method = RequestMethod.GET)
    public CommonReturnType closeOrder(@PathVariable String orderId) throws BusinessException {

        OrderModel orderModel = orderService.closeOrder(orderId);
        if (orderModel.getReturnResult() == "success") {
            return CommonReturnType.create(null);
        }else {
            return CommonReturnType.create(orderModel.getReturnResult(),"fail");
        }
    }


    @ApiOperation("通过id删除单个订单")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
    public CommonReturnType deleteOrder(@PathVariable String[] ids) throws BusinessException {

        if (ids == null) {
            return null;
        }
        for (String id:
             ids) {
            orderService.deleteOrder(id);
        }
        return CommonReturnType.create(null);

    }



}
