package com.wy.service;

import com.wy.common.error.BusinessException;
import com.wy.service.model.VideoUserModel;

/**
 * 描述：视频用户的服务
 * @author
 * @date 2019/12/27
 */

public interface VideoUserService {

    /**
     * 添加用户信息
     * @param videoUserModel
     * @return
     */
    VideoUserModel addVideoUserInfo(VideoUserModel videoUserModel) throws BusinessException;

    /**
     * 判断视频用户信息是否存在
     * @param phone
     * @return
     */
    VideoUserModel validateVideoUserIsExistByPhone(String phone) throws BusinessException;


}
