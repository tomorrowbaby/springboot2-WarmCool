package com.wy.service.impl;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wy.service.CacheService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


@Service
public class CacheServiceImpl implements CacheService {



        private Cache<String,Object> commonCache = null;

        //此注解在Spring进行工厂实例化的时候会优先加载
        @PostConstruct
        public void init(){
            commonCache = CacheBuilder.newBuilder()
                    //设置缓存容器的初始容量为10
                    .initialCapacity(10)
                    //设置缓存中最大可以存储100个key，超过100会按lru策略淘汰
                    .maximumSize(100)
                    //设置缓存写入后多少秒过期
                    .expireAfterWrite(60, TimeUnit.SECONDS).build();
        }


    @Override
    public void setCommonCache(String key, Object value) {

    }

    @Override
    public Object getFromCommonCache(String key) {
        return null;
    }
}
