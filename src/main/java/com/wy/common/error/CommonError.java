package com.wy.common.error;

/**
 * 描述：通用错误接口
 * @author wangyu
 * @date 2019/8/4
 */
public interface CommonError {

    int getErrCode();

    String getErrMessage();

    CommonError setErrMessage(String errMsg) ;
}
