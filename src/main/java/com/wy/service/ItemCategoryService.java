package com.wy.service;

import com.wy.common.error.BusinessException;
import com.wy.service.model.ItemCategoryModel;
import com.wy.service.model.ItemModel;

import java.util.List;

/**
 * 描述：商品信息分类
 * @author wangyu
 * @date 2019/8/16
 */

public interface ItemCategoryService {

    //通过父Id获取分类列表
    List<ItemCategoryModel> getItemCategoryListByParentId(Long parentId) throws BusinessException;


    //修改分类信息
    ItemCategoryModel updateItemCategory(ItemCategoryModel itemCategoryModel) throws BusinessException;

    //添加分类信息
    ItemCategoryModel addItemCategory(ItemCategoryModel itemCategoryModel) throws BusinessException;

    //删除分类信息
    ItemCategoryModel delItemCategoryById(Long id) throws BusinessException;
}
