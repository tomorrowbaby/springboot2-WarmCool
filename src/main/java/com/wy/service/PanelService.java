package com.wy.service;

import com.wy.service.model.PanelModel;

import java.util.List;

/**
 * 描述：首页接口
 * @author wangyu
 * @date 2019/10/14
 */

public interface PanelService {

    //首页模块列表获取
    List<PanelModel> getPanelList();
}
