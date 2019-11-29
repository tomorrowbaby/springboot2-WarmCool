package com.wy.controller;


import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnType;
import com.wy.controller.viewobject.ItemCategoryVO;
import com.wy.service.ItemCategoryService;
import com.wy.service.model.ItemCategoryModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/item/category")
@ResponseBody
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ItemCategoryController extends BaseController{

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     *通过父Id获取商品分类列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<ItemCategoryVO> getItemCategoryList(@RequestParam(value = "id",defaultValue = "0") Long pId) throws BusinessException {
            List<ItemCategoryModel> itemCategoryModels = itemCategoryService.getItemCategoryListByParentId(pId);
            List<ItemCategoryVO> itemCategoryVOS = new LinkedList<>();
            for (ItemCategoryModel model:
                 itemCategoryModels) {
                    itemCategoryVOS.add(this.convertFromItemCategoryModel(model));
            }
            return itemCategoryVOS;
    }

    /**
     *修改商品分类信息
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public CommonReturnType updateItemCategory(@ModelAttribute ItemCategoryModel itemCategoryModel) throws BusinessException {
        if (itemCategoryModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        itemCategoryService.updateItemCategory(itemCategoryModel);

        if (itemCategoryModel.getReturnResult() == "success") {
           return CommonReturnType.create("修改成功");
        }

        return CommonReturnType.create(itemCategoryModel.getReturnResult(),"fail");
    }

    /**
     *商品分类信息添加
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public CommonReturnType addItemCategory(@ModelAttribute ItemCategoryModel itemCategoryModel) throws BusinessException {
        if (itemCategoryModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        String result = itemCategoryService.addItemCategory(itemCategoryModel).getReturnResult();
        if (result == "success") {
            return CommonReturnType.create("添加成功");
        }
        return CommonReturnType.create(result,"fail");
    }


    /**
     * 商品分类信息删除
     */
    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    public CommonReturnType deleteItemCategory(@PathVariable("id") Long id) throws BusinessException {
        if (id == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        String result = itemCategoryService.delItemCategoryById(id).getReturnResult();

        if (result == "success"){
            return CommonReturnType.create("删除成功");
        }

        return CommonReturnType.create(result,"fail");
    }


    private ItemCategoryVO convertFromItemCategoryModel(ItemCategoryModel itemCategoryModel) {
        if (itemCategoryModel == null) {
            return null;
        }
        ItemCategoryVO itemCategoryVO = new ItemCategoryVO();

        BeanUtils.copyProperties(itemCategoryModel,itemCategoryVO);

        itemCategoryVO.setpId(itemCategoryModel.getParentId());

        return itemCategoryVO;
    }
}
