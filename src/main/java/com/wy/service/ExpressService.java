package com.wy.service;


import com.wy.common.error.BusinessException;
import com.wy.dataobject.ExpressDO;
import com.wy.service.model.ExpressModel;

import java.util.List;

/**
 * 描述：快递Service接口
 * @author wangyu
 * @date 2020/2/27
 */

public interface ExpressService {

    /**
     * 描述：获取快递列表
     * @return
     */
    List<ExpressModel> getExpressList() throws BusinessException;

    /**
     * 描述：修改快递信息
     * @param id
     * @param order_sort
     * @param shippingName
     * @return
     */
    ExpressModel editExpressInfo(Integer id,Integer order_sort,String shippingName) throws BusinessException;


    /**
     * 描述：添加快递信息
     * @param expressModel
     * @return
     */
    ExpressModel addExpressInfo(ExpressModel expressModel) throws BusinessException;

    /**
     * 描述：删除快递信息
     * @param id
     * @return
     */
    ExpressModel deleteExpressInfo(Integer id) throws BusinessException;
}
