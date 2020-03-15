package com.wy.controller;

import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.common.response.CommonReturnType;
import com.wy.controller.viewobject.ContentVO;
import com.wy.service.ContentService;
import com.wy.service.model.PanelContentModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：板块内容控制层
 * @author wangyu
 * @date 2020/3/1
 */


@RestController
@Api("板块内容接口")
@RequestMapping("/content")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ContentController extends BaseController{


    @Autowired
    private ContentService contentService;

    @ApiOperation("通过板块Id获取板块内容")
    @RequestMapping(value = "/list/{panelId}" ,method = RequestMethod.GET)
    public CommonReturnType getContentListByPanelId(@PathVariable Integer panelId) throws BusinessException {
        List<PanelContentModel> panelContentModelList = contentService.getContentByPanelId(panelId);
        List<ContentVO> contentVOList = new ArrayList<>();
        for (PanelContentModel p:
             panelContentModelList) {
            ContentVO contentVO = new ContentVO();
            BeanUtils.copyProperties(p,contentVO);
            contentVO.setCreateTime(p.getCreateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            contentVO.setUpdateTime(p.getUpdateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            contentVOList.add(contentVO);
        }

        return CommonReturnType.createData(contentVOList);
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加板块内容")
    public CommonReturnType addContent(@ModelAttribute PanelContentModel panelContentModel) throws BusinessException {

        contentService.addPanelContent(panelContentModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "编辑板块内容")
    public CommonReturnType updateContent(@ModelAttribute PanelContentModel panelContentModel) throws BusinessException {

        contentService.updateContent(panelContentModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除板块内容")
    public CommonReturnType addContent(@PathVariable int[] ids) throws BusinessException {

        for(int id:ids){
            contentService.deletePanelContent(id);
        }
        return CommonReturnType.create(null);
    }
}
