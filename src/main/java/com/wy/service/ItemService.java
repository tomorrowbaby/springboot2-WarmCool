package com.wy.service;

import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.service.model.ESModel;
import com.wy.service.model.ItemModel;

import java.util.List;

/**
 * 描述：商品服务接口
 * @author wangyu
 * @date 2019/8/22
 */

public interface ItemService {

    //获取商品数量
    ItemModel getItemCountByState(Integer state) throws BusinessException;


    CommonReturnPageInfo getItemListByPage(Integer cid,Integer start, Integer length,
                                           String searchKey, String orderCol,
                                           String orderDir, String minDate, String maxDate) throws BusinessException;



    ItemModel addItem(ItemModel itemModel) throws BusinessException;

    //修改商品信息
    ItemModel updateItem(ItemModel itemModel) throws BusinessException;

    //删除商品信息
    ItemModel deleteItemById(Long itemId) throws BusinessException;

    //商品状态设置
    ItemModel setItemStatus(Long itemId,Integer status) throws BusinessException;

    //通过Id获取商品
    ItemModel getItemById(Long id) throws BusinessException;

}
