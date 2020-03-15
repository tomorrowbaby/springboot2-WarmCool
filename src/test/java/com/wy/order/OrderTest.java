package com.wy.order;


import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.common.status.EmStatus;
import com.wy.dao.OrderDOMapper;
import com.wy.dataobject.OrderDO;
import com.wy.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {


    @Autowired
    OrderService orderService;

    @Autowired
    OrderDOMapper orderDOMapper;

    @Test
    public void testOrderList() throws BusinessException {


        System.out.println(orderService.selectAllOrderCount());

        CommonReturnPageInfo info = orderService.getListOrderByPage(0,10,null,null,null);



        System.out.println(info.getData().get(2));
    }

    @Test
    public void testOrderRemark() throws BusinessException {

        OrderDO orderDO = new OrderDO();
        orderDO.setBuyerMessage("123");
        orderDO.setOrderId("1");
        orderDOMapper.updateByPrimaryKeySelective(orderDO);
    }

    @Test
    public void testClsoeOrder(){
        OrderDO orderDO = new OrderDO();

        //设置关闭订单的状态
        orderDO.setStatus(EmStatus.ORDER_CLOSE.getStatusCode());
        orderDO.setOrderId("1");
        orderDO.setCloseTime(new Date());
        orderDO.setUpdateTime(new Date());
        orderDOMapper.updateByPrimaryKeySelective(orderDO);
    }

}
