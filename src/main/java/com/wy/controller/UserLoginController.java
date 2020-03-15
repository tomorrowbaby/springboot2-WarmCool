package com.wy.controller;


import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnType;
import com.wy.service.MemberService;
import com.wy.service.model.MemberModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 移动微信端登录接口
 * @author
 */

@Controller
@ResponseBody
@RequestMapping(value = "/login")
public class UserLoginController extends BaseController{


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "用户微信登录")
    @ApiImplicitParam(name="username" ,value = "用户名")
    @RequestMapping(value = "/wx",method = RequestMethod.GET)
    public CommonReturnType wxLogin(String username) {

        return null;
    }

    @ApiOperation(value = "用户手机号码短信登录or注册")
    @RequestMapping(value = "/mobile",method = RequestMethod.GET)
    public CommonReturnType mobileLogin(String mobile) throws BusinessException {
        if ("".equals(mobile)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }



        //将用户加入到Session

        //生成Token UUID
        String uuidToken = UUID.randomUUID().toString();
        uuidToken = uuidToken.replace("-","");
        redisTemplate.opsForValue().set(uuidToken,null);
        redisTemplate.expire(uuidToken,1, TimeUnit.HOURS);

        return CommonReturnType.create(uuidToken);
    }


    @ApiOperation(value = "移动端用户密码登陆")
    @RequestMapping(value = "/mobile/password" ,method = RequestMethod.POST)
    public CommonReturnType loginByPassword(String mobile,String password) throws BusinessException {
        if (mobile == null || password == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // 1.验证手机用户是否存在

        if (!memberService.validateMemberInfoIsExist(mobile)) {
            return CommonReturnType.create("用户不存在,请使用短信验证登陆注册","fail");
        }

        MemberModel memberModel = memberService.selectMemberInfoByPhone(mobile);

        //未绑定移动端
        if (new BCryptPasswordEncoder().matches(memberModel.getPassword(),password)) {
            //生成Token UUID
            String uuidToken = UUID.randomUUID().toString();
            uuidToken = uuidToken.replace("-","");
            redisTemplate.opsForValue().set(uuidToken,null);
            redisTemplate.expire(uuidToken,1, TimeUnit.HOURS);
            return CommonReturnType.create(uuidToken);
        }
        return CommonReturnType.create("手机号或密码不正确","fail");

    }

}
