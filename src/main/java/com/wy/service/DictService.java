package com.wy.service;



import com.wy.common.error.BusinessException;
import com.wy.service.model.DictModel;

import java.util.List;

/**
 * 描述：字典接口
 * @author wangyu
 * @date 2020/3/9
 */
public interface DictService {

    /**
     * 获取扩展词库列表
     * @return
     */
    List<DictModel> getDictList() throws BusinessException;

    /**
     * 获取停用词库列表
     * @return
     */
    List<DictModel> getStopList() throws BusinessException;

    /**
     * 添加
     * @param dictModel
     * @return
     */
    DictModel addDict(DictModel dictModel) throws BusinessException;

    /**
     * 更新
     * @param dictModel
     * @return
     */
    DictModel updateDict(DictModel dictModel) throws BusinessException;

    /**
     * 删除
     * @param id
     * @return
     */
    DictModel delDict(Integer id) throws BusinessException;
}
