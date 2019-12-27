package com.wy.controller;


import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnType;
import com.wy.service.MemberService;
import com.wy.service.VideoUserService;
import com.wy.service.model.MemberModel;
import com.wy.service.model.VideoUserModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 */

@Controller
@ResponseBody
@RequestMapping(value = "/login")
public class UserLoginController extends BaseController{

    @Autowired
    private VideoUserService videoUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "用户微信登录")
    @ApiImplicitParam(name="username" ,value = "用户名")
    @RequestMapping(value = "/wx",method = RequestMethod.GET)
    public CommonReturnType wxLogin(String username) {

        return null;
    }

    @ApiOperation(value = "用户手机号码短信登录or注册")
    @RequestMapping(value = "/mobile",method = RequestMethod.POST)
    public CommonReturnType mobileLogin(String mobile) throws BusinessException {
        if ("".equals(mobile)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        VideoUserModel videoUserModel = new VideoUserModel();

        if(videoUserService.validateVideoUserIsExistByPhone(mobile).getReturnResult().equals("false")){
            //注册
            //随机生成一个用户名

            videoUserModel.setNickName("用户"+DateTime.now());
            videoUserModel.setPhone(mobile);
            videoUserModel.setPhone(mobile);
            videoUserService.addVideoUserInfo(videoUserModel);

        }else {
            videoUserModel = videoUserService.validateVideoUserIsExistByPhone(mobile);
        }

        //将用户加入到Session

        //生成Token UUID
        String uuidToken = UUID.randomUUID().toString();
        uuidToken = uuidToken.replace("-","");
        System.out.println(uuidToken);
        redisTemplate.opsForValue().set(uuidToken,videoUserModel);
        redisTemplate.expire(uuidToken,1, TimeUnit.HOURS);

        return CommonReturnType.create(uuidToken);

    }


}
