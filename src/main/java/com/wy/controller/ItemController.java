package com.wy.controller;

import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.common.response.CommonReturnType;
import com.wy.service.ItemService;
import com.wy.service.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 描述：商品控制层
 * @author wangyu
 * @date 2019/8/22
 */


@Controller
@RequestMapping("/item")
@ResponseBody
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品数量
     */
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public CommonReturnType getItemCount() throws BusinessException {
        return CommonReturnType.create(itemService.getItemCountByState(null).getCount());
    }

    /**
     * 根据条件分页查询商品
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonReturnPageInfo getItemList(int draw, int start, int length, int cid,
                                                  @RequestParam("search[value]") String search,
                                                  @RequestParam("order[0][column]") int orderCol,
                                                  @RequestParam("order[0][dir]") String orderDir
                                                 ) throws BusinessException {
        String[] map = {"checkbox","id", "image", "title", "sell_point", "price", "created", "updated", "status"};

        String orderColumn = map[orderCol];

        //默认排序的列
        if(orderColumn == null) {
            orderColumn = "created";
        }
        //获取排序方式
        if(orderDir == null) {
            orderDir = "desc";
        }
        CommonReturnPageInfo commonReturnPageInfo = new CommonReturnPageInfo();
        commonReturnPageInfo.setDraw(draw);

        commonReturnPageInfo = itemService.getItemListByPage(cid,start,length,search,orderColumn,orderDir,null,null);

        return commonReturnPageInfo;
    }

    /*
    * 条件查询商品
    */
    @RequestMapping(value = "/list/search" ,method = RequestMethod.GET)
    public CommonReturnPageInfo searchItemList(int draw, int start, int length, String searchKey,
                                               String minDate,String maxDate,
                                               @RequestParam("order[0][column]") int orderCol,
                                               @RequestParam("order[0][dir]") String orderDir) throws BusinessException {
        String[] map = {"checkbox","id", "image", "title", "sell_point", "price", "created", "updated", "status"};

        String orderColumn = map[orderCol];

        //默认排序的列
        if(orderColumn == null) {
            orderColumn = "created";
        }
        //获取排序方式
        if(orderDir == null) {
            orderDir = "desc";
        }
        CommonReturnPageInfo commonReturnPageInfo = new CommonReturnPageInfo();
        commonReturnPageInfo.setDraw(draw);

        commonReturnPageInfo = itemService.getItemListByPage(-1,start,length,searchKey,orderColumn,orderDir,minDate,maxDate);

        return commonReturnPageInfo;
    }

    /**
     * 添加商品
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public CommonReturnType addItem(@ModelAttribute ItemModel itemModel) throws BusinessException, IOException {

        if (itemModel == null || itemModel.getNum() == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        itemModel.setStock(itemModel.getNum().longValue());
        String result = itemService.addItem(itemModel).getReturnResult();

        if ("success".equals(result)) {
            return CommonReturnType.create("添加成功");
        }

        return CommonReturnType.create(result,"fail");
    }

    /**
     * 商品发布
     */
    @RequestMapping(value = "/start/{id}",method = RequestMethod.PUT)
    public CommonReturnType setItemToStart(@PathVariable("id") Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ItemModel itemModel = itemService.setItemStatus(id,1);
        String result = itemModel.getReturnResult();
        if ("success".equals(result)) {
            return CommonReturnType.create("发布成功！");
        }else {
            return CommonReturnType.create(result,"fail");
        }

    }


    /**
     * 商品下架
     */
    @RequestMapping(value = "/stop/{id}",method = RequestMethod.PUT)
    public CommonReturnType setItemToStop(@PathVariable("id") Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ItemModel itemModel = itemService.setItemStatus(id,0);
        String result = itemModel.getReturnResult();
        if ("success".equals(result)) {
            return CommonReturnType.create("下架成功！");
        }else {
            return CommonReturnType.create(result,"fail");
        }

    }

    /**
     * 单个商品信息删除
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public CommonReturnType deleteItem(@PathVariable("id") Long id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ItemModel itemModel = itemService.deleteItemById(id);
        String result = itemModel.getReturnResult();

        if ("success".equals(result)) {
            return CommonReturnType.create("删除成功");
        }else {
            return CommonReturnType.create(result,"fail");
        }
    }

    /**
     *批量删除
     */
    @RequestMapping(value = "/delete/list/{ids}",method = RequestMethod.DELETE)
    public CommonReturnType deleteItemList(@PathVariable Long[] ids) throws BusinessException {
        if (ids == null || ids.length == 0) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        for(Long id: ids) {
            ItemModel itemModel = itemService.deleteItemById(id);
        }
        return CommonReturnType.create("删除成功！");
    }

    /**
     * 通过id查询商品信息
     */
    @RequestMapping(value = "{id}" ,method = RequestMethod.GET)
    public CommonReturnType getItemById(@PathVariable("id") Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ItemModel itemModel = itemService.getItemById(id);
        if ("success".equals(itemModel.getReturnResult())) {
            return CommonReturnType.create(itemModel);
        }
        return CommonReturnType.create("获取信息失败！","fail");
    }


    /**
     * 修改商品信息
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonReturnType updateItem(@ModelAttribute ItemModel itemModel,@PathVariable("id") Long id) throws BusinessException {
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        itemModel.setId(id);
        itemModel.setStock(itemModel.getNum().longValue());
        ItemModel returnItemModel = itemService.updateItem(itemModel);
        if ("success".equals(returnItemModel.getReturnResult())) {
            return CommonReturnType.create("修改成功！");
        }
        return CommonReturnType.create(returnItemModel.getReturnResult(),"fail");
    }


}
