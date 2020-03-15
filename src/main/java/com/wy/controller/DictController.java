package com.wy.controller;


import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.common.response.CommonReturnType;
import com.wy.common.constant.DictConstant;
import com.wy.service.DictService;
import com.wy.service.model.DictModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *描述：字典控制
 * @author wangyu
 * @date 2020/3/9
 */
@RestController
@Api("词典库")
@RequestMapping("/dict")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class DictController extends BaseController{

    @Autowired
    private DictService dictService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/getDictList",method = RequestMethod.GET)
    @ApiOperation(value = "获得所有扩展词库")
    public String getDictExtList(HttpServletResponse response) throws BusinessException {

        String result = "";
        String v = (String) redisTemplate.opsForValue().get(DictConstant.DICT_EXT);
        if(StringUtils.isNotBlank(v)){
            return v;
        }
        List<DictModel> list=dictService.getDictList();
        for(DictModel dictModel : list){
            result += dictModel.getDict() + "\n";
        }
        if(StringUtils.isNotBlank(result)) {
            redisTemplate.opsForValue().set(DictConstant.DICT_EXT,result);
        }
        response.addHeader(DictConstant.LAST_MODIFIED, String.valueOf(redisTemplate.opsForValue().get(DictConstant.LAST_MODIFIED)));
        response.addHeader(DictConstant.ETAG, String.valueOf(redisTemplate.opsForValue().get(DictConstant.ETAG)));
        return result;
    }

    @RequestMapping(value = "/getStopDictList",method = RequestMethod.GET)
    @ApiOperation(value = "获得所有扩展词库")
    public String getStopDictList(HttpServletResponse response) throws BusinessException {

        String result = "";
        String v = (String) redisTemplate.opsForValue().get(DictConstant.STOP_KEY);
        if(StringUtils.isNotBlank(v)){
            return v;
        }
        List<DictModel> list=dictService.getStopList();
        for(DictModel dictModel : list){
            result += dictModel.getDict() + "\n";
        }
        if(StringUtils.isNotBlank(result)){
            redisTemplate.opsForValue().set(DictConstant.STOP_KEY, result);
        }
        response.addHeader(DictConstant.LAST_MODIFIED, String.valueOf(redisTemplate.opsForValue().get(DictConstant.LAST_MODIFIED)));
        response.addHeader(DictConstant.ETAG, String.valueOf(redisTemplate.opsForValue().get(DictConstant.ETAG)));
        return result;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "获得所有扩展词库")
    public CommonReturnPageInfo getDictList() throws BusinessException {

        CommonReturnPageInfo commonReturnPageInfo = new CommonReturnPageInfo();
        List<DictModel> list = dictService.getDictList();
        commonReturnPageInfo.setData(list);
        return commonReturnPageInfo;
    }

    @RequestMapping(value = "/stop/list",method = RequestMethod.GET)
    @ApiOperation(value = "获得所有停用词库")
    public CommonReturnPageInfo getStopList() throws BusinessException {

        CommonReturnPageInfo commonReturnPageInfo = new CommonReturnPageInfo();
        List<DictModel> list=dictService.getStopList();
        commonReturnPageInfo.setData(list);
        return commonReturnPageInfo;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加词典")
    public CommonReturnType addDict(@ModelAttribute DictModel dictModel) throws BusinessException {

        dictModel = dictService.addDict(dictModel);
        update();
        if (dictModel.getReturnResult() == "success") {
            return CommonReturnType.create(null);
        }
        return CommonReturnType.create(dictModel.getReturnResult(),"fail");

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "编辑词典")
    public CommonReturnType updateDict(@ModelAttribute DictModel dictModel) throws BusinessException {

        dictModel = dictService.updateDict(dictModel);
        update();
        if (dictModel.getReturnResult() == "success") {
            return CommonReturnType.create(null);
        }
        return CommonReturnType.create(dictModel.getReturnResult(),"fail");

    }

    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除词典")
    public CommonReturnType delDict(@PathVariable int[] ids) throws BusinessException {

        for(int id:ids){
            dictService.delDict(id);
        }
        update();
        return CommonReturnType.create(null);
    }

    public void update(){
        //更新词典标识
        redisTemplate.opsForValue().set(DictConstant.LAST_MODIFIED, String.valueOf(System.currentTimeMillis()));
        redisTemplate.opsForValue().set(DictConstant.ETAG, String.valueOf(System.currentTimeMillis()));
        //更新缓存
        redisTemplate.delete(DictConstant.EXT_KEY);
        redisTemplate.delete(DictConstant.STOP_KEY);
    }
}
