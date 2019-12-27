package com.wy.utils;
import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSDK.BodyType;
import com.wy.common.response.CommonReturnType;

import java.util.HashMap;
import java.util.Set;

/**
 * 描述：短信工具类
 * @author
 * @date 2019/12/26
 */

public class MessageUtil {



    public static CommonReturnType sendMessageByMobilePhone(String phone,String code) {

                HashMap<String, Object> result = null;
                CCPRestSDK restAPI = new CCPRestSDK();
                restAPI.init("app.cloopen.com", "8883");
                // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
                restAPI.setAccount("8a216da86f17653b016f400816f01c3c", "53724efb71c945e3ab3e845d65f0ebf8");
                // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
                restAPI.setAppId("8a216da86f17653b016f400818961c42");
                // 请使用管理控制台中已创建应用的APPID。
                result = restAPI.sendTemplateSMS(phone,"01" ,new String[]{code,"3分钟"});
                System.out.println("SDKTestGetSubAccounts result=" + result);
                if("000000".equals(result.get("statusCode"))){
                    //正常返回输出data包体信息（map）
                    HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
                    Set<String> keySet = data.keySet();
                    for(String key:keySet){
                        Object object = data.get(key);
                        System.out.println(key +" = "+object);
                    }
                    return CommonReturnType.create("success");
                }else{
                    //异常返回输出错误码和错误信息
                    System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
                    return CommonReturnType.create(result.get("statusCode"),(String) result.get("statusMsg"));
                }

    }
}
