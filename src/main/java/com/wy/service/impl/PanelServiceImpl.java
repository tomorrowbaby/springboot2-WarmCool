package com.wy.service.impl;

import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.dao.PanelDOMapper;
import com.wy.dataobject.PanelDO;
import com.wy.dataobject.PanelDOExample;
import com.wy.service.PanelService;
import com.wy.service.model.PanelModel;
import com.wy.service.model.ZTreeNode;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Service
public class PanelServiceImpl implements PanelService {

    @Autowired
    PanelDOMapper panelDOMapper;

    @Override
    public List<PanelModel> getPanelList() {



        return null;
    }

    @Override
    public PanelModel getPanelById(Integer id) throws BusinessException {

        if (id == null) {
            return null;
        }

        PanelDO panelDO = null;

        try {
            panelDO = panelDOMapper.selectByPrimaryKey(id);
        }catch (Exception E){
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        PanelModel panelModel = new PanelModel();

        if (panelDO != null) {
            BeanUtils.copyProperties(panelDO,panelModel);
            panelModel.setCreateTime(new DateTime(panelDO.getCreateTime()));
            panelModel.setUpdateTime(new DateTime(panelDO.getUpdateTime()));
            panelModel.setReturnResult("success");

        }else {
            panelModel.setReturnResult("不存在该Id板块");
        }
        return panelModel;

    }

    @Override
    public List<ZTreeNode> getPanelList(Integer position, boolean showAll) {
        PanelDOExample example=new PanelDOExample();
        PanelDOExample.Criteria criteria=example.createCriteria();
        if(position==0&&!showAll){
            //除去非轮播
            criteria.andTypeNotEqualTo(0);
        }else if(position==-1){
            //仅含轮播
            position=0;
            criteria.andTypeEqualTo(0);
        }
        //首页板块
        criteria.andPositionEqualTo(position);
        example.setOrderByClause("sort_order");
        List<PanelDO> panelList = panelDOMapper.selectByExample(example);

        List<ZTreeNode> zTreeNodeList = new ArrayList<>();

        for(PanelDO panelDO:panelList){

            ZTreeNode zTreeNode = new ZTreeNode();
            BeanUtils.copyProperties(panelDO,zTreeNode);
            zTreeNode.setpId(0);
            zTreeNode.setIsParent(false);
            zTreeNodeList.add(zTreeNode);
        }

        return zTreeNodeList;
    }

    @Override
    @Transactional
    public PanelModel addPanel(PanelModel panelModel) throws BusinessException {

        if (panelModel == null) {
            return null;
        }

        if(panelModel.getType() == 0){
            PanelDOExample example = new PanelDOExample();
            PanelDOExample.Criteria criteria = example.createCriteria();
            criteria.andTypeEqualTo(0);
            List<PanelDO> list = panelDOMapper.selectByExample(example);

            //已经存在轮播图
            if(list!=null&&list.size()>0){
                panelModel.setReturnResult("已经存在轮播图");
                return panelModel;
            }
        }
        int row = 0;

        PanelDO panelDO = new PanelDO();
        BeanUtils.copyProperties(panelModel,panelDO);
        panelDO.setCreateTime(new Date());
        panelDO.setUpdateTime(new Date());
        try{
            row = panelDOMapper.insert(panelDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

       if (row == 1) {
           panelModel.setReturnResult("success");
       }else {
           panelModel.setReturnResult("插入失败");
       }
//        //同步缓存
//        deleteHomeRedis();
        return panelModel;
    }

    @Override
    @Transactional
    public PanelModel updatePanel(PanelModel panelModel) throws BusinessException {

        if (panelModel == null) {
            return null;
        }

        PanelDO panelDO = new PanelDO();
        BeanUtils.copyProperties(panelModel,panelDO);
        panelDO.setUpdateTime(new Date());

        int row = 0;
        try{
            row = panelDOMapper.updateByPrimaryKeySelective(panelDO);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }
//        //同步缓存
//        deleteHomeRedis();
        if (row == 1) {
            panelModel.setReturnResult("success");
        }else {
            panelModel.setReturnResult("修改失败");
        }
        return panelModel;
    }

    @Override
    @Transactional
    public PanelModel deletePanel(Integer id) throws BusinessException {

        if (id == null) {
            return null;
        }

        int row = 0;
        try {
            panelDOMapper.deleteByPrimaryKey(id);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        PanelModel panelModel = new PanelModel();

        if (row == 1) {
            panelModel.setReturnResult("success");
        }else {
            panelModel.setReturnResult("不存在该id的用户");
        }
        return panelModel;
    }
}
