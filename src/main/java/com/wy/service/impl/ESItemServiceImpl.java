package com.wy.service.impl;

import com.google.gson.Gson;
import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.config.ServerUrlConfig;
import com.wy.dao.ItemDOMapper;
import com.wy.dataobject.es.SearchItemDO;
import com.wy.service.ESItemService;
import com.wy.service.model.ESCountModel;
import com.wy.service.model.ESModel;
import com.wy.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.wy.dao.repository.ESItemRepository;

import java.util.List;


/**
 * @author Exrickx
 */
@Service
public class ESItemServiceImpl implements ESItemService {

    private static Logger logger = LogManager.getLogger(ESItemServiceImpl.class) ;

	@Autowired
	private ItemDOMapper itemDOMapper;

	@Autowired
    ESItemRepository esItemRepository;
	@Override
	public int importAllItems() throws BusinessException {

		try{

			//查询商品列表
			List<SearchItemDO> itemList = itemDOMapper.getItemList();

			logger.info("开始导入ES索引");

			//遍历商品列表
			for (SearchItemDO searchItem : itemList) {
				String image=searchItem.getProductImageBig();
				if (image != null && !"".equals(image)) {
					String[] strings = image.split(",");
					image=strings[0];
				}else{
					image="";
				}
				searchItem.setProductImageBig(image);
			}
			Iterable<SearchItemDO> searchItemDOIterable = itemList;

			esItemRepository.saveAll(searchItemDOIterable);

			logger.info("更新索引成功");

		}catch (Exception e){
			e.printStackTrace();
			throw new BusinessException(EmBusinessError.ES_IMPORT_FAIL);
		}

		return 1;
	}


	@Override
	public int refreshItem(int type, Long itemId) {

		try {
			//更新单个索引索引
			if(type==0){
				//根据商品id查询商品信息
				SearchItemDO searchItem = itemDOMapper.getItemById(itemId);
				String image=searchItem.getProductImageBig();
				if (image != null && !"".equals(image)) {
					String[] strings = image.split(",");
					image=strings[0];
				}else{
					image="";
				}
				searchItem.setProductImageBig(image);
				esItemRepository.deleteById(itemId.intValue());
				esItemRepository.save(searchItem);
			}else{
				esItemRepository.deleteById(itemId.intValue());
			}

			logger.info("同步商品："+itemId+"索引成功");


		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public ESModel getEsInfo() throws BusinessException {

		String healthUrl="http://"+ ServerUrlConfig.ESServer +"/_cluster/health";
		String countUrl="http://"+ServerUrlConfig.ESServer+"/_count";

		String healthResult= HttpUtil.sendGet(healthUrl);
		String countResult= HttpUtil.sendGet(countUrl);
		if(StringUtils.isBlank(healthResult)||StringUtils.isBlank(countResult)){
			throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
		}
		ESModel esInfo=new ESModel();
		ESCountModel esCount=new ESCountModel();
		try {
			esInfo=new Gson().fromJson(healthResult,ESModel.class);
			esCount=new Gson().fromJson(countResult, ESCountModel.class);
			esInfo.setCount(esCount.getCount());
		}catch (Exception e){
			e.printStackTrace();
			throw new BusinessException(EmBusinessError.ES_CONNECT_FAIL);
		}

		return esInfo;
	}
}
