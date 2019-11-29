package com.wy.common.response;

/**
 * 描述：返回kindeditor插件json类型
 * @author
 * @date 2019/9/7
 */


public class KindeditorImageReturnType {

    //返回图片路径
    private String url;

    //返回错误码
    private Integer error;

    //返回错误信息
    private String message;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
