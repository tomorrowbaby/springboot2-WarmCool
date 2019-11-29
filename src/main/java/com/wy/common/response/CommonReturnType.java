package com.wy.common.response;

import java.util.List;

/**
 * 描述：通用返回类
 * @author wangyu
 * @date 2019/8/4
 */

public class CommonReturnType {

    //返回的请求信息，success或者fail
    private String status ;

    private Object result;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }


    public void setResult(Object result) {
        this.result = result;
    }

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success") ;
    }

    public static CommonReturnType create(Object result,String status){
        CommonReturnType type = new CommonReturnType() ;
        type.setResult(result);
        type.setStatus(status);
        return type;
    }

}

