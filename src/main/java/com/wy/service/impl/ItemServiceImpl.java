package com.wy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.dao.ItemDOMapper;
import com.wy.dao.ItemDescDOMapper;
import com.wy.dao.ItemSalesDOMapper;
import com.wy.dao.ItemStockDOMapper;
import com.wy.dataobject.ItemDO;
import com.wy.dataobject.ItemDescDO;
import com.wy.dataobject.ItemStockDO;
import com.wy.service.ItemService;
import com.wy.service.model.ItemModel;
import com.wy.validator.ValidationResult;
import com.wy.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述：商品业务
 * @author wangyu
 * @date 2019/8/22
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Autowired
    private ItemSalesDOMapper itemSalesDOMapper;

    @Autowired
    private ItemDescDOMapper itemDescDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public ItemModel getItemCountByState(Integer state) throws BusinessException {

        ItemModel itemModel = new ItemModel();

        long itemCount = 0 ;
        try {
            itemCount = itemDOMapper.selectItemCount(state);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        itemModel.setCount(itemCount);
        return itemModel;
    }

    @Override
    public CommonReturnPageInfo getItemListByPage(Integer cid,Integer start, Integer length, String searchKey, String orderCol, String orderDir, String minDate, String maxDate) throws BusinessException {

        if (start == null && length ==  null ) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if (!StringUtils.equals(orderDir,"desc")) {
            orderDir = "asc";
        }

        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%"+searchKey+"%";
        }

        PageHelper.startPage(start/length+1,length);
        Page<ItemDO> itemDOPage = new Page<>();

        try {
            itemDOPage = itemDOMapper.selectPageItemByItemInfo(new Long(cid),searchKey,minDate,maxDate,orderCol,orderDir);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        List<ItemDO> itemDOS = new LinkedList<>();
        CommonReturnPageInfo commonReturnPageInfo = new CommonReturnPageInfo();
        itemDOS = itemDOPage.getResult();
        commonReturnPageInfo.setData(itemDOS);
        commonReturnPageInfo.setRecordsTotal(Long.bitCount(this.getItemCountByState(null).getCount()));
        commonReturnPageInfo.setRecordsFiltered((int)itemDOPage.getTotal());

        return commonReturnPageInfo;
    }

    @Transactional
    @Override
    public ItemModel addItem(ItemModel itemModel) throws BusinessException {

        ValidationResult validationResult = validator.validate(itemModel);

        if (validationResult.isHasError()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        itemModel.setCreated(new DateTime());
        itemModel.setUpdated(new DateTime());

        ItemStockDO itemStockDO = new ItemStockDO();
        ItemDescDO itemDescDO = new ItemDescDO();
        itemStockDO.setStock(itemModel.getStock());

        int rows = 0;
        int stockRows = 0;
        try {
            rows = itemDOMapper.insertSelective(this.convertFromItemModel(itemModel));
            itemStockDO.setItemId(itemModel.getId());
            itemDescDO.setItemId(itemModel.getId());
            stockRows = itemStockDOMapper.insertSelective(itemStockDO);
            itemDescDOMapper.insertSelective(itemDescDO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (rows > 0) {
            itemModel.setReturnResult("success");
        }else if (stockRows <= 0) {
            itemModel.setReturnResult("商品库存添加失败！");
        }else {
            itemModel.setReturnResult("添加商品信息失败！");
        }
        return itemModel;
    }

    @Transactional
    @Override
    public ItemModel updateItem(ItemModel itemModel) throws BusinessException {

        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidationResult validationResult = validator.validate(itemModel);

        if (validationResult.isHasError()) {
            itemModel.setReturnResult(validationResult.getErrMsg());
            return itemModel;
        }

        itemModel.setUpdated(new DateTime());

        ItemDO itemDO = this.convertFromItemModel(itemModel);
        ItemStockDO itemStockDO = this.convertFromItemModelToItemStock(itemModel);

        Integer rows = 0;
        try {
            rows = itemDOMapper.updateByPrimaryKeySelective(itemDO);
            itemStockDOMapper.updateByPrimaryKeySelective(itemStockDO);
            itemDescDOMapper.updateByPrimaryKey(this.convertFromItemModelToItemDescDO(itemModel));
        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if (rows > 0) {
            itemModel.setReturnResult("success");
        }else {
            itemModel.setReturnResult("修改失败");
        }

        return itemModel;
    }

    @Transactional
    @Override
    public ItemModel deleteItemById(Long itemId) throws BusinessException {

        if (itemId == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ItemModel itemModel = new ItemModel();

        Integer rows = 0;

        try {
            rows = itemDOMapper.deleteByPrimaryKey(itemId);
            itemStockDOMapper.deleteByItemId(itemId);
            itemSalesDOMapper.deleteByItemId(itemId);
        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        if (rows > 0) {
            itemModel.setReturnResult("success");
        } else {
            itemModel.setReturnResult("删除的用户信息不存在！");
        }

        return itemModel;
    }

    @Transactional
    @Override
    public ItemModel setItemStatus(Long itemId, Integer status) throws BusinessException {
        if (itemId == null || status == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ItemModel itemModel = new ItemModel();
        itemModel.setId(itemId);
        itemModel.setStatus(status);

        int rows = 0 ;

        ItemDO itemDO = this.convertFromItemModel(itemModel);
        try{
            rows = itemDOMapper.updateByPrimaryKeySelective(itemDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (rows > 0 ) {
            itemModel.setReturnResult("success");
        }else {
            itemModel.setReturnResult("状态设置失败");
        }

        return itemModel;
    }

    @Override
    public ItemModel getItemById(Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ItemModel itemModel = new ItemModel();
        try {
            ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(id);
            itemModel = this.convertFromItemDO(itemDO,itemStockDO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (itemModel != null) {
            itemModel.setReturnResult("success");
        }else {
            itemModel.setReturnResult("用户信息不存在！");
        }

        return itemModel;
    }


    private ItemDO convertFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }

        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        if (itemModel.getCreated() != null ) {
            itemDO.setCreated(itemModel.getCreated().toDate());
        }
        if (itemModel.getUpdated() != null) {
            itemDO.setUpdated(itemModel.getUpdated().toDate());
        }

        return itemDO;
    }

    private ItemStockDO convertFromItemModelToItemStock(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }

        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setStock(itemModel.getStock());
        itemStockDO.setItemId(itemModel.getId());

        return itemStockDO;
    }


    private ItemModel convertFromItemDO(ItemDO itemDO,ItemStockDO itemStockDO) {


        if (itemDO == null && itemStockDO == null) {
            return null;
        }
        ItemModel itemModel = new ItemModel();

        BeanUtils.copyProperties(itemDO,itemModel);
        BeanUtils.copyProperties(itemStockDO,itemModel);

        if (itemDO.getCreated() != null) {
            itemModel.setCreated(new DateTime(itemDO.getCreated()));
        }
        if (itemDO.getUpdated() != null) {
            itemModel.setUpdated(new DateTime(itemDO.getUpdated()));
        }

        return itemModel;
    }

     private ItemDescDO convertFromItemModelToItemDescDO(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }

        ItemDescDO itemDescDO = new ItemDescDO();

        if (itemModel.getId() != null){
            itemDescDO.setItemId(itemModel.getId());
        }
        if (itemModel.getDescription() != null) {
            itemDescDO.setItemDesc(itemModel.getDescription());
        }
         if (itemModel.getCreated().toDate() != null) {
             itemDescDO.setCreated(itemModel.getCreated().toDate());
         }
         if (itemModel.getUpdated().toDate() != null) {
             itemDescDO.setUpdated(itemModel.getUpdated().toDate());
         }

         return itemDescDO;

    }






}
