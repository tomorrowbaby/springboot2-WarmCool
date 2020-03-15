package com.wy.service.impl;


import com.wy.dao.MemberDOMapper;
import com.wy.dao.MemberRoleDOMapper;
import com.wy.dao.PasswordDOMapper;
import com.wy.dao.RoleDOMapper;
import com.wy.dataobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：用户登陆验证身份绑定
 * @author wangyu
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RoleDOMapper roleDOMapper;

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Autowired
    private PasswordDOMapper passwordDOMapper;

    @Autowired
    private MemberRoleDOMapper memberRoleDOMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询用户表
        MemberDO memberDO = memberDOMapper.selectMemberInfoByUsername(s);
        if (memberDO == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //用户密码
        PasswordDO passwordDO = passwordDOMapper.selectByMemberId(memberDO.getId());

        MemberRoleDOExample memberRoleDOExample = new MemberRoleDOExample();
        MemberRoleDOExample.Criteria criteria = memberRoleDOExample.createCriteria();
        criteria.andMemberIdEqualTo(memberDO.getId());

        List<MemberRoleDO> memberRoleDOList = memberRoleDOMapper.selectByExample(memberRoleDOExample);

        //用户角色
        Integer roleId = memberRoleDOList.get(0).getRoleId();
        RoleDO roleDO = roleDOMapper.selectByPrimaryKey(roleId);


        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>() ;

        //暂时使用单个用户只对应一个身份
        if (roleId != null ){
                authorityList.add(new SimpleGrantedAuthority(roleDO.getName())) ;
        }

        //这块必须返回new org.springframework.security.core.userdetails.UserDO
        //不能返回上面的user
        return new org.springframework.security.core.userdetails.User(memberDO.getUsername(),passwordDO.getEncrptPassword(),authorityList);
    }
}
