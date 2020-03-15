package com.wy.common.status;


/**
 * 描述：通用状态接口
 * @author wangyu
 * @date 2020/2/26
 */


public interface CommonStatus {

    int getStatusCode();

    String getStatusInfo();

    CommonStatus setStatusInfo(String errMsg) ;
}
