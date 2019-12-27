package com.wy.controller;


import com.cloopen.rest.sdk.utils.EncryptUtil;
import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnType;
import com.wy.service.MemberService;
import com.wy.service.model.MemberModel;
import com.wy.utils.EncryptionUtil;
import com.wy.utils.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 描述：短信登录验证
 * @author wangyu
 * @date 2019/12/25
 */

@Controller
@RequestMapping(value = "/message")
@ResponseBody
public class MessageController extends BaseController{

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "发送手机号码短信验证")
    @RequestMapping(value = "/mobile",method = RequestMethod.GET)
    public CommonReturnType sendMobileCode(String mobile) throws BusinessException, IOException, NoSuchAlgorithmException {

        if ("".equals(mobile)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //生成验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String optCode = String.valueOf(randomInt);

        //发送验证码
        try {
            MessageUtil.sendMessageByMobilePhone(mobile,optCode);
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.Message_SERVICE_ERROR);
        }

        return CommonReturnType.create(optCode,"success");
    }
}
