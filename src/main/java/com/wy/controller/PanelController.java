package com.wy.controller;

import com.wy.common.response.CommonReturnType;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：视图版块控制层
 * @author wangyu
 * @date 2019/10/4
 */

@Controller
@RequestMapping("/panel")
@ResponseBody
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class PanelController extends BaseController{


    @RequestMapping(value = "/indexBanner/list",method = RequestMethod.GET)
    @ApiOperation(value = "首页banner图轮播列表获取")
    public CommonReturnType getIndexPanelList(){



        return null;

    }
}
