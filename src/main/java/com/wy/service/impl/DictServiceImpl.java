package com.wy.service.impl;


import com.wy.common.constant.DictConstant;
import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.dao.DictDOMapper;
import com.wy.dataobject.DictDO;
import com.wy.dataobject.DictDOExample;
import com.wy.service.DictService;
import com.wy.service.model.DictModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDOMapper dictDOMapper;




    @Override
    public List<DictModel> getDictList() throws BusinessException {

        DictDOExample example=new DictDOExample();
        DictDOExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andTypeEqualTo(DictConstant.DICT_EXT);

        List<DictDO> dictDOList = null;

        try {
            dictDOList = dictDOMapper.selectByExample(example);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        List<DictModel> dictModelList = new ArrayList<>();

        for (DictDO dict:
             dictDOList) {
            DictModel dictModel = new DictModel();
            BeanUtils.copyProperties(dict,dictModel);
            dictModelList.add(dictModel);
        }


        if (dictDOList != null) {
             return  dictModelList;
        }
        return null;

    }

    @Override
    public List<DictModel> getStopList() throws BusinessException {

        DictDOExample example=new DictDOExample();
        DictDOExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andTypeEqualTo(DictConstant.DICT_STOP);


        List<DictDO> dictDOList = new ArrayList<>();

        try {
            dictDOList = dictDOMapper.selectByExample(example);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        List<DictModel> dictModelList = new ArrayList<>();

        for (DictDO dict:
                dictDOList) {
            DictModel dictModel = new DictModel();
            BeanUtils.copyProperties(dict,dictModel);
            dictModelList.add(dictModel);
        }

        if (dictDOList != null) {
            return  dictModelList;
        }
        return null;
    }

    @Override
    public DictModel addDict(DictModel dictModel) throws BusinessException {
        if (dictModel == null){
            return null;
        }
        DictDO dictDO = new DictDO();
        BeanUtils.copyProperties(dictModel,dictDO);

        int row = 0;
        try {
            row = dictDOMapper.insert(dictDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        if (row != 1) {
            dictModel.setReturnResult("修改失败");
        }else {
            dictModel.setReturnResult("success");
        }
        return dictModel;
    }

    @Override
    public DictModel updateDict(DictModel dictModel) throws BusinessException {
        if (dictModel == null){
            return null;
        }
        DictDO dictDO = new DictDO();
        BeanUtils.copyProperties(dictModel,dictDO);

        int row = 0;
        try {
            row = dictDOMapper.updateByPrimaryKey(dictDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (row != 1) {
            dictModel.setReturnResult("修改失败");
        }else {
            dictModel.setReturnResult("success");
        }

        return dictModel;
    }

    @Override
    public DictModel delDict(Integer id) throws BusinessException {

        if (id == null) {
            return null;
        }

        int row = 0;
        try {
            row = dictDOMapper.deleteByPrimaryKey(id);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        DictModel dictModel = new DictModel();
        if (row != 1) {
            dictModel.setReturnResult("不存在该id的用户");
        }else {
            dictModel.setReturnResult("success");
        }
        return dictModel;
    }
}
