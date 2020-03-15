package com.wy.service.impl;

import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.dao.ItemDOMapper;
import com.wy.dao.PanelContentDOMapper;
import com.wy.dataobject.ItemDO;
import com.wy.dataobject.PanelContentDO;
import com.wy.dataobject.PanelContentDOExample;
import com.wy.service.ContentService;
import com.wy.service.model.PanelContentModel;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {


    @Autowired
    PanelContentDOMapper panelContentDOMapper;


    @Autowired
    ItemDOMapper itemDOMapper;

    @Override
    public List<PanelContentModel> getContentByPanelId(Integer panelId) throws BusinessException {

        if (panelId == null) {
            return null;
        }

        PanelContentDOExample panelContentDOExample = new PanelContentDOExample();
        PanelContentDOExample.Criteria criteria= panelContentDOExample.createCriteria();
        criteria.andPanelIdEqualTo(panelId);

        List<PanelContentModel> panelContentModelList = new ArrayList<>();
        List<PanelContentDO> panelContentDOList = null;
        try{
            panelContentDOList = panelContentDOMapper.selectByExample(panelContentDOExample);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }


        if (panelContentDOList != null) {
            for (PanelContentDO panelContentDO:
                 panelContentDOList) {
                ItemDO itemDO = itemDOMapper.selectByPrimaryKey(panelContentDO.getProductId());
                PanelContentModel panelContentModel = new PanelContentModel();
                panelContentModel.setProductName(itemDO.getTitle());
                panelContentModel.setSalePrice(itemDO.getPrice());
                panelContentModel.setSubTitle(itemDO.getSellPoint());
                panelContentModel.setCreateTime(new DateTime(panelContentDO.getCreateTime()));
                panelContentModel.setUpdateTime(new DateTime(panelContentDO.getUpdateTime()));
                BeanUtils.copyProperties(panelContentDO,panelContentModel);
                panelContentModelList.add(panelContentModel);
            }
        }

        return panelContentModelList;
    }

    @Override
    @Transactional
    public PanelContentModel addPanelContent(PanelContentModel panelContentModel) throws BusinessException {

        if (panelContentModel == null) {
            return null;
        }

        PanelContentDO panelContentDO = new PanelContentDO();
        BeanUtils.copyProperties(panelContentModel,panelContentDO);
        panelContentDO.setCreateTime(new Date());
        panelContentDO.setUpdateTime(new Date());

        int row = 0;
        try{
            row = panelContentDOMapper.insertSelective(panelContentDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (row == 1) {
            panelContentModel.setReturnResult("success");
        }else {
            panelContentModel.setReturnResult("插入数据失败");        }

        return panelContentModel;
    }

    @Override
    @Transactional
    public PanelContentModel deletePanelContent(Integer id) throws BusinessException {

        if (id == null) {
            return null;
        }

        int row = 0;
        try{
            row = panelContentDOMapper.deleteByPrimaryKey(id);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        PanelContentModel panelContentModel = new PanelContentModel();
        if (row == 1) {
            panelContentModel.setReturnResult("success");
        }else {
            panelContentModel.setReturnResult("不存在该id用户");        }

        return panelContentModel;
    }

    @Override
    @Transactional
    public PanelContentModel updateContent(PanelContentModel panelContentModel) throws BusinessException {

        if (panelContentModel == null) {
            return null;
        }
        PanelContentDO panelContentDO = new PanelContentDO();
        if ("" == panelContentModel.getPicUrl()){
            panelContentDO.setPicUrl(null);
        }
        BeanUtils.copyProperties(panelContentModel,panelContentDO);
        panelContentDO.setUpdateTime(new Date());
        int row = 0;
        try{
            row = panelContentDOMapper.updateByPrimaryKeySelective(panelContentDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
        if (row == 1) {
            panelContentModel.setReturnResult("success");
        }else {
            panelContentModel.setReturnResult("不存在该id用户");        }

        return panelContentModel;

    }

    @Override
    public PanelContentModel getPanelContentById(Integer id) throws BusinessException {
        if (id == null) {
            return null;
        }

        PanelContentDO panelContentDO = null;

        try{
            panelContentDO = panelContentDOMapper.selectByPrimaryKey(id);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        PanelContentModel panelContentModel = new PanelContentModel();

        if (panelContentDO != null) {
            BeanUtils.copyProperties(panelContentDO,panelContentModel);
            panelContentModel.setCreateTime(new DateTime(panelContentDO.getCreateTime()));
            panelContentModel.setUpdateTime(new DateTime(panelContentDO.getUpdateTime()));
            panelContentModel.setReturnResult("success");
        }else {
            panelContentModel.setReturnResult("未找到该id的用户");
        }

        return panelContentModel;
    }

    @Override
    public List<PanelContentModel> getHome() {
        return null;
    }

    @Override
    public List<PanelContentModel> getRecommendGoods() {
        return null;
    }

    @Override
    public String getIndexRedis() {
        return null;
    }

    @Override
    public int updateIndexRedis() {
        return 0;
    }

    @Override
    public String getRecommendRedis() {
        return null;
    }

    @Override
    public int updateRecommendRedis() {
        return 0;
    }

    @Override
    public String getThankRedis() {
        return null;
    }

    @Override
    public int updateThankRedis() {
        return 0;
    }

    @Override
    public List<PanelContentModel> getNavList() {
        return null;
    }
}
