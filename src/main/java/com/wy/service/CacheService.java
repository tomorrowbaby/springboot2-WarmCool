package com.wy.service;

/**
 * 描述：缓存接口
 * @author
 * @date 2019/12/25
 */


public interface CacheService {

    //将热点数据key，value存入到本地内存
    void setCommonCache(String key,Object value);

    //取方法
    Object getFromCommonCache(String key);
}
