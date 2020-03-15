package com.wy.service;

import com.wy.common.error.BusinessException;
import com.wy.service.model.PanelModel;
import com.wy.service.model.ZTreeNode;

import java.util.List;

/**
 * 描述：首页接口
 * @author wangyu
 * @date 2019/10/14
 */

public interface PanelService {

    //首页模块列表获取
    List<PanelModel> getPanelList();

    /**
     * 通过id获取板块
     * @param id
     * @return
     */
    PanelModel getPanelById(Integer id) throws BusinessException;

    /**
     * 获取板块类目
     * @param position
     * @param showAll
     * @return
     */
    List<ZTreeNode> getPanelList(Integer position, boolean showAll);

    /**
     * 添加板块
     * @param panelModel
     * @return
     */
    PanelModel addPanel(PanelModel panelModel) throws BusinessException;

    /**
     * 更新板块
     * @param panelModel
     * @return
     */
    PanelModel updatePanel(PanelModel panelModel) throws BusinessException;

    /**
     * 删除板块
     * @param id
     * @return
     */
    PanelModel deletePanel(Integer id) throws BusinessException;
}
