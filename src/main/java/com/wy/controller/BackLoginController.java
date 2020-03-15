package com.wy.controller;


import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnType;
import com.wy.service.MemberService;
import com.wy.service.UserLoginService;
import com.wy.service.model.MemberModel;
import com.wy.utils.ValidateCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Api("后台登陆控制")
@RestController
@RequestMapping("/login")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class BackLoginController extends BaseController{


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private UserLoginService userLoginService;


    @Autowired
    private MemberService memberService;


    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping(value = "/username",method = RequestMethod.POST)
    public CommonReturnType backLogin(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      HttpSession session) throws BusinessException {

        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
            CommonReturnType.create("参数错误","fail");
        }

        MemberModel memberModel = userLoginService.validateLogin(username,password);

        if (memberModel.getReturnResult() == "success") {
            //生成Token UUID
            String uuidToken = UUID.randomUUID().toString();
            uuidToken = uuidToken.replace("-","");
            System.out.println(uuidToken);
            redisTemplate.opsForValue().set(uuidToken,memberModel);
            redisTemplate.expire(uuidToken,1, TimeUnit.HOURS);
            return CommonReturnType.create(uuidToken);
        }

        return CommonReturnType.create("用户名或密码错误","fail");
    }



    /**
     * @param checkCode 前端用户输入返回的验证码
     * 参数若需要，自行添加
     */
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    public CommonReturnType checkcode(@RequestParam("code") String checkCode,
                            @RequestParam("uuid") String uuid) throws Exception {

        // 获得验证码对象
        if (StringUtils.isEmpty(uuid) ||StringUtils.isEmpty(checkCode)){
            return null;
        }

        String code = redisTemplate.opsForValue().get(uuid).toString();
        if (StringUtils.isEmpty(code) || !StringUtils.equalsIgnoreCase(checkCode,code)) {
            return CommonReturnType.create("验证码错误","fail");
        }else {
            return CommonReturnType.create(null);
        }


    }

    /**
     * 用于生成带四位数字验证码的图片
     */
    @RequestMapping(value = "/captcha/{uuid}" , method = RequestMethod.GET)
    @ApiOperation("验证码接口")
    public String createImageCode(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @PathVariable String uuid) throws Exception {

        //返回验证码和图片的map
        ValidateCodeUtil validateCodeUtil = new ValidateCodeUtil(100,40,4,60);

        System.out.println(uuid);
        redisTemplate.opsForValue().set(uuid,validateCodeUtil.getCode());
        redisTemplate.expire(uuid,3, TimeUnit.MINUTES);

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        OutputStream os = response.getOutputStream();


        try {
            ImageIO.write((BufferedImage) validateCodeUtil.getBuffImg(), "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }   finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
        return null;
    }


    @RequestMapping(value = "/user/{uuid}",method = RequestMethod.GET)
    @ApiOperation("获取登陆的用户信息")
    public CommonReturnType getLoginUser(@PathVariable String uuid) {
        if (uuid == null) {
            return null;
        }

        MemberModel memberModel = (MemberModel) redisTemplate.opsForValue().get(uuid);

        if (memberModel == null) {
            return CommonReturnType.create("用户信息已过期！","fail");
        }
        return CommonReturnType.create(memberModel);
    }

    @RequestMapping(value = "/logout/{uuid}",method = RequestMethod.GET)
    @ApiOperation("退出登陆接口")
    public CommonReturnType loginOut(@PathVariable String uuid) {
        if (uuid == null) {
            return null;
        }

       redisTemplate.delete(uuid);

        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    @ApiOperation("权限登陆接口")
    private void checkUserInfo(String username,String password){

        UserDetails userDetails =userDetailsService.loadUserByUsername(username);

    }
}
