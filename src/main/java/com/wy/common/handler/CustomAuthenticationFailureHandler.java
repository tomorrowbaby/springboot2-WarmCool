package com.wy.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wy.service.impl.ESItemServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录失败回调函数
 * @author Created by niugang on 2019/5/23/14:55
 */
@SuppressWarnings("Duplicates")
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static Logger logger = LogManager.getLogger(ESItemServiceImpl.class) ;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        logger.info("Login fail");

        Map<String, Object> map = new HashMap<>(4);
        map.put("result", "用户名或密码错误");
        map.put("status", "fail");
        map.put("timestamp", System.currentTimeMillis());
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        //用户名或密码错误
        response.getWriter().write(objectMapper.writeValueAsString(map));


    }
}
