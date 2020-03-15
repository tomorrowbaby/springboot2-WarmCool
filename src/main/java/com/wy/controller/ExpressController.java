package com.wy.controller;


import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.common.response.CommonReturnType;
import com.wy.service.ExpressService;
import com.wy.service.model.ExpressModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("快递模块接口")
@RestController
@RequestMapping("/express")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ExpressController extends BaseController{

        @Autowired
        private ExpressService expressService;

        @ApiOperation("获取快递列表")
        @RequestMapping(value = "/list",method = RequestMethod.GET)
        public CommonReturnPageInfo getExpressList() throws BusinessException {

            List<ExpressModel> expressModelList = expressService.getExpressList();

            CommonReturnPageInfo commonReturnPageInfo = new CommonReturnPageInfo();
            commonReturnPageInfo.setData(expressModelList);
            commonReturnPageInfo.setStatus("success");

            return commonReturnPageInfo;

        }


        @ApiOperation("修改快递信息")
        @RequestMapping(value = "/update",method = RequestMethod.POST)
        public CommonReturnType editExpress(Integer id,String expressName,Integer sortOrder) throws BusinessException {
            if (id == null || expressName == null ||sortOrder == null) {
                return null;
            }
            ExpressModel expressModel = expressService.editExpressInfo(id,sortOrder,expressName);

            if (expressModel.getReturnResult() == "success") {
                return CommonReturnType.create(null);
            }else {
                return CommonReturnType.create(expressModel.getReturnResult(),"fail");
            }
        }


        @ApiOperation("添加快递信息")
        @RequestMapping(value = "/add",method = RequestMethod.POST)
        public CommonReturnType createExpress(@ModelAttribute ExpressModel expressModel) throws BusinessException {

            if(expressModel == null) {
                return null;
            }

            ExpressModel returnExpressModel = expressService.addExpressInfo(expressModel);

            if (expressModel.getReturnResult() == "success") {
                return CommonReturnType.create(null);
            }else {
                return CommonReturnType.create(expressModel.getReturnResult(),"fail");
            }

        }



        @ApiOperation("删除快递信息")
        @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
        public CommonReturnType deleteExpress(@PathVariable Integer[] ids) throws BusinessException {

            if (ids == null) {
                return null;
            }

            for (Integer id:
                 ids) {
                expressService.deleteExpressInfo(id);
            }

            return CommonReturnType.create(null);

        }





}
