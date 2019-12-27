package com.wy.service.impl;

import com.wy.common.response.CommonReturnPageInfo;
import com.wy.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public CommonReturnPageInfo listOrders(String orderId, Integer start, Integer length, String searchKey, String sortColumn, String sortBy, String minDate, String maxDate) {
        return null;
    }
}
