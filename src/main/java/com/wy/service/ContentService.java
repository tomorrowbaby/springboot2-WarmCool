package com.wy.service;

import com.wy.common.error.BusinessException;
import com.wy.service.model.PanelContentModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述：板块内容
 * @author wangyu
 * @date 2020/3/1
 */

public interface ContentService {
    /**
     * 描述：通过板块id获取板块内容列表
     * @param panelId
     * @return
     */
    List<PanelContentModel> getContentByPanelId(Integer panelId) throws BusinessException;

    /**
     * 添加板块内容
     * @param panelContentModel
     * @return
     */
    PanelContentModel addPanelContent(PanelContentModel panelContentModel) throws BusinessException;


    /**
     * 删除板块内容
     * @param id
     * @return
     */
    @Transactional
    PanelContentModel deletePanelContent(Integer id) throws BusinessException;

    /**
     * 编辑板块内容
     * @param panelContentModel
     * @return
     */
    PanelContentModel updateContent(PanelContentModel panelContentModel) throws BusinessException;

    /**
     * 通过id获取板块内容
     * @param id
     * @return
     */


    PanelContentModel getPanelContentById(Integer id) throws BusinessException;

    /**
     * 获取首页数据
     * @return
     */
    List<PanelContentModel> getHome();

    /**
     * 获取商品推荐板块
     * @return
     */
    List<PanelContentModel> getRecommendGoods();


//    /**
//     * 获取商品详情
//     * @param id
//     * @return
//     */
//    ProductDet getProductDet(Long id);
//
//    /**
//     * 分页多条件获取全部商品
//     * @param page
//     * @param size
//     * @param sort
//     * @param priceGt
//     * @param priceLte
//     * @return
//     */
//    AllGoodsResult getAllProduct(int page, int size, String sort, Long cid, int priceGt, int priceLte);

    /**
     * 获取首页缓存
     * @return
     */
    String getIndexRedis();

    /**
     * 同步首页缓存
     * @return
     */
    int updateIndexRedis();

    /**
     * 获取推荐板块缓存
     * @return
     */
    String getRecommendRedis();

    /**
     * 同步推荐板块缓存
     * @return
     */
    int updateRecommendRedis();

    /**
     * 获取推荐板块缓存
     * @return
     */
    String getThankRedis();

    /**
     * 同步推荐板块缓存
     * @return
     */
    int updateThankRedis();

    /**
     * 获取导航栏
     * @return
     */
    List<PanelContentModel> getNavList();
}
