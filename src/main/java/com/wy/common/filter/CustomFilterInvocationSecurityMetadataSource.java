package com.wy.common.filter;

import com.wy.dao.PermissionDOMapper;
import com.wy.dao.RoleDOMapper;
import com.wy.dao.RolePermDOMapper;
import com.wy.dataobject.PermissionDO;
import com.wy.dataobject.RoleDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 自定义的资源（url）权限（角色）数据获取类
 */
@Component("customFilterInvocationSecurityMetadataSource")
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    @Autowired
    PermissionDOMapper permissionDOMapper;

    @Autowired
    RoleDOMapper roleDOMapper;

    @Autowired
    RolePermDOMapper rolePermDOMapper;


    /**
     * 每个资源（url）所需要的权限（角色）集合
     */
    private static HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 获取每个资源（url）所需要的权限（角色）集合
     * 这里应该从数据库中动态查询，这里为了方便而直接创建
     */

    private void getResourcePermission(){
        map = new HashMap<>();

        //获取权限
        List<RoleDO> roleDOList = roleDOMapper.selectList();

        //获取所有的url
        List<PermissionDO> permissionDOList = permissionDOMapper.selectList();
        for (PermissionDO permissionDO:
                permissionDOList) {
            //找到url所需要的身份
            List<Integer> roleIdList = rolePermDOMapper.selectByPermissionId(permissionDO.getId());
            //配置身份列表
            List<ConfigAttribute> configAttributeList = new ArrayList<>();

            for (Integer roleId:
                 roleIdList) {
                RoleDO roleDO = roleDOMapper.selectByPrimaryKey(roleId);
                //配置身份
                ConfigAttribute configAttribute = new SecurityConfig(roleDO.getName());
                //添加到身份列表里
                configAttributeList.add(configAttribute);
            }
            //放入权限中
            if (configAttributeList.isEmpty()||configAttributeList.size() == 0){
                //未设置的权限以游客身份
                ConfigAttribute configAttribute = new SecurityConfig("ADMIN");
                configAttributeList.add(configAttribute);
                map.put(permissionDO.getPermission(),configAttributeList);
            }else {
                map.put(permissionDO.getPermission(),configAttributeList);
            }

        }

    }

    /**
     * 获取用户请求的某个具体的资源（url）所需要的权限（角色）集合
     * @param object 包含了用户请求的request信息
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map ==null) {
            getResourcePermission();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        /**
         * 遍历每个资源（url），如果与用户请求的资源（url）匹配，则返回该资源（url）所需要的权限（角色）集合，
         * 如果全都不匹配，则表示用户请求的资源（url)不需要权限（角色）即可访问
         */
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext()) {
            String url = iter.next();
            if(new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
