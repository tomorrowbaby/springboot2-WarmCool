package com.wy.dao.repository;

import com.wy.dataobject.es.SearchItemDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESItemRepository extends ElasticsearchRepository<SearchItemDO,Integer> {

}
