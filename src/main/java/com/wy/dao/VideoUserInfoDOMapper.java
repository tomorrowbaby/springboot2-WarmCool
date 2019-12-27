package com.wy.dao;

import com.wy.dataobject.VideoUserInfoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoUserInfoDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VideoUserInfoDO record);

    int insertSelective(VideoUserInfoDO record);

    VideoUserInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoUserInfoDO record);

    int updateByPrimaryKey(VideoUserInfoDO record);

    //通过手机号码查找用户是否存在
    VideoUserInfoDO selectByPhone(String phone);
}