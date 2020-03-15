package com.wy.service.impl;

import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.dao.MemberDOMapper;
import com.wy.dao.PasswordDOMapper;
import com.wy.dataobject.MemberDO;
import com.wy.dataobject.PasswordDO;
import com.wy.service.UserLoginService;
import com.wy.service.model.MemberModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    MemberDOMapper memberDOMapper;


    @Autowired
    PasswordDOMapper passwordDOMapper;

    @Override
    public MemberModel validateLogin(String username, String password) throws BusinessException {

        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return null;
        }

        MemberDO memberDO = null;

        try{
           memberDO =  memberDOMapper.selectMemberInfoByUsername(username);

        }catch (Exception e){
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        if (memberDO == null) {
            return null;
        }

        PasswordDO passwordDO = passwordDOMapper.selectByMemberId(memberDO.getId());

        if (passwordDO == null) {
            return null;
        }

        if (new BCryptPasswordEncoder().matches(password,passwordDO.getEncrptPassword()) ){
            MemberModel memberModel = new MemberModel();
            BeanUtils.copyProperties(memberDO,memberModel);
            memberModel.setReturnResult("success");
            return memberModel;
        }

        return null;
    }
}
