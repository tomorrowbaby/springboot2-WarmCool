package com.wy.service.impl;

import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.dao.ExpressDOMapper;
import com.wy.dataobject.ExpressDO;
import com.wy.service.ExpressService;
import com.wy.service.model.ExpressModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service
public class ExpressServiceImpl implements ExpressService {


    @Autowired
    private ExpressDOMapper expressDOMapper;

    @Override
    public List<ExpressModel> getExpressList() throws BusinessException {

        List<ExpressDO> expressDOList = new ArrayList<>();
        try {

            expressDOList = expressDOMapper.selectExpressList("desc");
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        List<ExpressModel> expressModelList = new ArrayList<>();

        Iterator<ExpressDO> expressDOIterator = expressDOList.listIterator();

        while (expressDOIterator.hasNext()) {
            expressModelList.add(this.convertFromExpressDOtoExpressModel(expressDOIterator.next()));
        }

        return expressModelList;
    }

    @Override
    public ExpressModel editExpressInfo(Integer id, Integer sortOrder, String expressName) throws BusinessException {

        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ExpressDO expressDO = new ExpressDO();
        expressDO.setId(id);
        expressDO.setSortOrder(sortOrder);
        expressDO.setExpressName(expressName);
        expressDO.setUpdateTime(new Date());

        int row = 0;
        try {
            row = expressDOMapper.updateByPrimaryKeySelective(expressDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        ExpressModel expressModel = new ExpressModel();
        if (row != 1) {
            expressModel.setReturnResult("不存在该ID的订单");
        }else {
            expressModel.setReturnResult("success");
        }

        return expressModel;
    }

    @Override
    public ExpressModel addExpressInfo(ExpressModel expressModel) throws BusinessException {

        if (expressModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        expressModel.setCreateTime(new DateTime());
        expressModel.setUpdateTime(new DateTime());

        ExpressDO expressDO = this.convertFromExpressModelToExpressDO(expressModel);

        int row = 0;
        try{
            row = expressDOMapper.insertSelective(expressDO);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (row != 1) {
            expressModel.setReturnResult("添加失败");
        }else {
            expressModel.setReturnResult("success");
        }
        return expressModel;
    }

    @Override
    public ExpressModel deleteExpressInfo(Integer id) throws BusinessException {
        if (id == null) {
            return null;
        }
        int row = 0;
        try {
            row = expressDOMapper.deleteByPrimaryKey(id);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        ExpressModel expressModel = new ExpressModel();

        if (row != 1) {
            expressModel.setReturnResult("不存在该id的用户");
        }else {
            expressModel.setReturnResult("success");
        }
        return expressModel;
    }


    private  ExpressModel  convertFromExpressDOtoExpressModel(ExpressDO expressDO) {
        if (expressDO == null) {
                return null;
        }

        ExpressModel expressModel = new ExpressModel();
        BeanUtils.copyProperties(expressDO,expressModel);
        if (expressDO.getCreateTime() != null) {
            expressModel.setCreateTime(new DateTime(expressDO.getCreateTime()));
        }
        if (expressDO.getUpdateTime() != null) {
            expressModel.setUpdateTime(new DateTime(expressDO.getUpdateTime()));
        }

        return expressModel;
    }

    private ExpressDO convertFromExpressModelToExpressDO(ExpressModel expressModel){
        if (expressModel == null) {
            return null;
        }
        ExpressDO expressDO = new ExpressDO();
        BeanUtils.copyProperties(expressModel,expressDO);
        if (expressModel.getCreateTime() != null) {
            expressDO.setCreateTime(new DateTime(expressModel.getCreateTime()).toDate());
        }
        if (expressModel.getUpdateTime() != null) {
            expressDO.setUpdateTime(new DateTime(expressModel.getUpdateTime()).toDate());
        }
        return expressDO;
    }
}
