package com.wy.service.manager;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.wy.common.response.CommonReturnType;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Set;

/**
 * 描述：集成短信SDK类
 * @author
 * @date 2019/12/26
 */

public class Message {

    private static Logger logger = Logger.getLogger(Message.class);

    public static CommonReturnType sendMessageByMobilePhone(String phone,String code) {

        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "8a216da86f17653b016f400816f01c3c";
        String accountToken = "53724efb71c945e3ab3e845d65f0ebf8";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8a216da86f17653b016f400818961c42";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String to = phone;
        String templateId= "01";
        String[] datas = {code,"3分钟","hi"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                logger.info(object);
            }

            return CommonReturnType.create(null);
        }else{
            //异常返回输出错误码和错误信息
            String errorResult = ("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            return CommonReturnType.create(errorResult,"fail");
        }
    }
}
