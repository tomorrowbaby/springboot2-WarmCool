package com.wy.service;

import com.wy.common.error.BusinessException;
import com.wy.service.model.MemberModel;

/**
 * 描述：用户登录接口
 */


public interface UserLoginService {


    MemberModel validateLogin(String username, String password) throws BusinessException;


}
