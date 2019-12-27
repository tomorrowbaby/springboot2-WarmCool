package com.wy.service.impl;

import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.dao.VideoUserInfoDOMapper;
import com.wy.dataobject.VideoUserInfoDO;
import com.wy.service.VideoUserService;
import com.wy.service.model.VideoUserModel;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VideoUserServiceImpl implements VideoUserService {

    @Autowired(required = false)
    private VideoUserInfoDOMapper videoUserInfoDOMapper;

    @Override
    public VideoUserModel addVideoUserInfo(VideoUserModel videoUserModel) throws BusinessException {
        if (videoUserModel == null) {
            return null;
        }

        VideoUserInfoDO videoUserInfoDO = this.convertFromVideoUserModel(videoUserModel);

        try {
            videoUserInfoDOMapper.insertSelective(videoUserInfoDO);
        }catch (Exception E) {
            throw new BusinessException(EmBusinessError.MYSQL_RUN_ERROR);
        }

        videoUserModel.setReturnResult("success");
        return videoUserModel;

    }

    @Override
    public VideoUserModel validateVideoUserIsExistByPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return null;
        }

        VideoUserInfoDO videoUserInfoDO = null;
        try {
            videoUserInfoDO = videoUserInfoDOMapper.selectByPhone(phone);
        }catch (Exception e) {

        }
        VideoUserModel videoUserModel = this.convertFromVideoUserInfoDO(videoUserInfoDO);
        if (videoUserModel == null) {
            videoUserModel.setReturnResult("false");
        }else {
            videoUserModel.setReturnResult("true");
        }

        return videoUserModel;
    }


    private VideoUserModel convertFromVideoUserInfoDO(VideoUserInfoDO videoUserInfoDO) {
        if (videoUserInfoDO == null) {
            return null;
        }
        VideoUserModel videoUserModel = new VideoUserModel();
        BeanUtils.copyProperties(videoUserInfoDO,videoUserModel);

        if (videoUserInfoDO.getCreateTime() != null){
            videoUserModel.setCreateTime(new DateTime(videoUserInfoDO.getCreateTime()));
        }

        if (videoUserInfoDO.getUpdateTime() != null) {
            videoUserModel.setUpdateTime(new DateTime(videoUserInfoDO.getUpdateTime()));
        }


        return videoUserModel;
    }

    private VideoUserInfoDO convertFromVideoUserModel(VideoUserModel videoUserModel){
        if (videoUserModel == null) {
            return  null;
        }

        VideoUserInfoDO videoUserInfoDO = new VideoUserInfoDO();

        BeanUtils.copyProperties(videoUserModel,videoUserInfoDO);

        if (videoUserModel.getCreateTime() != null) {
            videoUserInfoDO.setCreateTime(videoUserModel.getCreateTime().toDate());
        }
        if (videoUserModel.getUpdateTime() != null) {
            videoUserInfoDO.setUpdateTime(videoUserModel.getUpdateTime().toDate());
        }

        return videoUserInfoDO;

    }
}
