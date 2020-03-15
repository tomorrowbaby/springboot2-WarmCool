package com.wy.service;

import com.wy.common.error.BusinessException;
import com.wy.service.model.ESModel;

public interface ESItemService {

    /**
     * 同步索引
     * @return
     */
    int importAllItems() throws BusinessException;

    /**
     * 同步单个商品索引
     * @param type
     * @param itemId
     * @return
     */
    int refreshItem(int type,Long itemId);

    /**
     * 获取ES基本信息
     * @return
     */
    ESModel getEsInfo() throws BusinessException;
}
