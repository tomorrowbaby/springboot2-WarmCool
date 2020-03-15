package com.wy.controller;

import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnType;
import com.wy.service.PanelService;
import com.wy.service.model.PanelModel;
import com.wy.service.model.ZTreeNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：视图版块控制层
 * @author wangyu
 * @date 2019/10/4
 */

@Api("视图板块接口")
@RestController
@RequestMapping("/panel")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class PanelController extends BaseController{


    @Autowired
    private PanelService panelService;

    @RequestMapping(value = "/index/list",method = RequestMethod.GET)
    @ApiOperation(value = "获得首页板块列表不含轮播")
    public List<ZTreeNode> getIndexPanel(){

        List<ZTreeNode> list=panelService.getPanelList(0,false);
        return list;
    }

    @RequestMapping(value = "/indexAll/list",method = RequestMethod.GET)
    @ApiOperation(value = "获得首页板块列表含轮播")
    public List<ZTreeNode> getAllIndexPanel(){

        List<ZTreeNode> list=panelService.getPanelList(0,true);
        return list;
    }

    @RequestMapping(value = "/indexBanner/list",method = RequestMethod.GET)
    @ApiOperation(value = "获得首页轮播板块列表")
    public List<ZTreeNode> getIndexBannerPanel(){

        List<ZTreeNode> list=panelService.getPanelList(-1,true);
        return list;
    }

    @RequestMapping(value = "/other/list",method = RequestMethod.GET)
    @ApiOperation(value = "获得其它添加板块")
    public List<ZTreeNode> getRecommendPanel(){

        List<ZTreeNode> list=panelService.getPanelList(1,false);
        list.addAll(panelService.getPanelList(2,false));
        return list;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加板块")
    public CommonReturnType addContentCategory(@ModelAttribute PanelModel panelModel) throws BusinessException {

        if (panelModel == null) {
            return null;
        }

        panelService.addPanel(panelModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "编辑内容分类")
    public CommonReturnType updateContentCategory(@ModelAttribute PanelModel panelModel) throws BusinessException {

        if (panelModel == null){
            return  null;
        }

        panelModel = panelService.updatePanel(panelModel);
        if (panelModel.getReturnResult() == "success"){
            return CommonReturnType.createData(panelModel);
        }
        return CommonReturnType.create(panelModel.getReturnResult(),"fail");
    }

    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除内容分类")
    public CommonReturnType deleteContentCategory(@PathVariable int[] ids) throws BusinessException {

        if (ids == null) {
            return null;
        }

        for(int id:ids){
            panelService.deletePanel(id);
        }

        return CommonReturnType.create(null);
    }

}
