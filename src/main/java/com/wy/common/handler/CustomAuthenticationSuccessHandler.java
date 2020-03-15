package com.wy.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wy.common.response.CommonReturnType;
import com.wy.service.MemberService;
import com.wy.service.UserLoginService;
import com.wy.service.impl.ESItemServiceImpl;
import com.wy.service.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 描述：登录成功回调函数
 * @author wangyu
 * @date 2020/3/12
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static Logger logger = LogManager.getLogger(ESItemServiceImpl.class) ;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    MemberService memberService;

    @Autowired
    UserLoginService userLoginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("Login success");
        response.setContentType("application/json;charset=UTF-8");
        //authentication封装了明细
        Map<String,Object> result = new HashMap<>();
        result.put("status","success");
        result.put("auth",authentication);

        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

}
