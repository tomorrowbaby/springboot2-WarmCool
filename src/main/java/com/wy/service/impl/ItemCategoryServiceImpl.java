package com.wy.service.impl;

import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.dao.ItemCategoryDOMapper;
import com.wy.dataobject.ItemCategoryDO;
import com.wy.dataobject.ItemCategoryDOExample;
import com.wy.service.ItemCategoryService;
import com.wy.service.model.ItemCategoryModel;
import com.wy.validator.ValidationResult;
import com.wy.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired(required=false)
    private ItemCategoryDOMapper itemCategoryDOMapper;

    @Override
    public List<ItemCategoryModel> getItemCategoryListByParentId(Long parentId) throws BusinessException {

        if (parentId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ItemCategoryDOExample itemCategoryDOExample = new ItemCategoryDOExample();
        ItemCategoryDOExample.Criteria criteria = itemCategoryDOExample.createCriteria();

        //设置数据库中的排序字段
        itemCategoryDOExample.setOrderByClause("sort_order");

       //设置查询条件
       criteria.andParentIdEqualTo(parentId);

        List<ItemCategoryDO> itemCategoryDOs = new LinkedList<>();
        try{
            itemCategoryDOs = itemCategoryDOMapper.selectByExample(itemCategoryDOExample);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        List<ItemCategoryModel> itemCategoryModels = new LinkedList<>();
        for (ItemCategoryDO itemCategoryDO: itemCategoryDOs) {
            itemCategoryModels.add(this.convertFromItemCategoryDO(itemCategoryDO));
        }
        return itemCategoryModels;
    }

    @Override
    @Transactional
    public ItemCategoryModel updateItemCategory(ItemCategoryModel itemCategoryModel) throws BusinessException {
        if (itemCategoryModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ItemCategoryDO itemCategoryDO = this.convertFromItemCategoryModel(itemCategoryModel);

        int row = 0;
        try {
            row = itemCategoryDOMapper.updateByPrimaryKeySelective(itemCategoryDO);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (row > 0) {
            itemCategoryModel.setReturnResult("success");
        }else {
            itemCategoryModel.setReturnResult("未修改成功！");
        }


        return itemCategoryModel;
    }

    @Override
    public ItemCategoryModel addItemCategory(ItemCategoryModel itemCategoryModel) throws BusinessException {

        if (itemCategoryModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        itemCategoryModel.setSortOrder(1);
        ItemCategoryDO itemCategoryDO = this.convertFromItemCategoryModel(itemCategoryModel);

        int row = 0;
        try {
            row = itemCategoryDOMapper.insertSelective(itemCategoryDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (row > 0) {
            itemCategoryModel.setReturnResult("success");
        }else {
            itemCategoryModel.setReturnResult("添加0条数据");
        }

        return itemCategoryModel;
    }

    @Override
    @Transactional
    public ItemCategoryModel delItemCategoryById(Long id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();

        int row = 0;
        try {
            row = itemCategoryDOMapper.deleteByPrimaryKey(id);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        if (row > 0) {
            itemCategoryModel.setReturnResult("success");
        }else {
            itemCategoryModel.setReturnResult("未找到改id号的用户");
        }
        return itemCategoryModel;
    }


    private ItemCategoryModel convertFromItemCategoryDO(ItemCategoryDO itemCategoryDO) {
        if (itemCategoryDO == null) {
            return null;
        }
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        BeanUtils.copyProperties(itemCategoryDO,itemCategoryModel);

        return itemCategoryModel;

    }


    private ItemCategoryDO convertFromItemCategoryModel(ItemCategoryModel itemCategoryModel) {
        if (itemCategoryModel == null) {
            return null;
        }
        ItemCategoryDO itemCategoryDO = new ItemCategoryDO();
        BeanUtils.copyProperties(itemCategoryModel,itemCategoryDO);
        return itemCategoryDO;
    }
}
