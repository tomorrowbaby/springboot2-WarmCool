package com.wy.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义spring security身份验证机制
 *
 * @author Created by niugang on 2019/5/23/11:07
 */
@SuppressWarnings("Duplicates")
@Controller
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class CustomAuthenticationController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 当需要身份认证时，跳转到这里
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public void requireAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        Map<String, Object> map = new HashMap<>(4);
        map.put("message", "访问的服务需要身份认证,请认证");
        map.put("status", HttpStatus.UNAUTHORIZED.value());
        map.put("timestamp", System.currentTimeMillis());
        ObjectMapper objectMapper = new ObjectMapper();
        //其他访问，如前后端分离，前端根据返回的状态码路由到对应的页面
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));

    }


}
