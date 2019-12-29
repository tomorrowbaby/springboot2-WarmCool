package com.wy.video.user;


import com.wy.common.error.BusinessException;
import com.wy.service.VideoUserService;
import com.wy.service.model.VideoUserModel;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoUserTest {

    @Autowired
    private VideoUserService videoUserService;

    @Test
    public void VideoUserTest() throws BusinessException {
        VideoUserModel videoUserModel = videoUserService.validateVideoUserIsExistByPhone("15304727515");
        System.out.println(videoUserModel.getReturnResult());
    }
    @Test
    public void VideoUserAddTest() throws BusinessException {
        VideoUserModel videoUserModel = new VideoUserModel();
        //注册
        //随机生成一个用户名
        videoUserModel.setNickName("用户"+ DateTime.now().getMillis());
        videoUserModel.setPhone("15304727515");
        videoUserModel.setFansCount(0);
        videoUserModel.setFollowCount(0);
        videoUserModel.setReveiveLikeCount(0);
        videoUserModel.setCreateTime(new DateTime());
        videoUserModel.setUpdateTime(new DateTime());

        videoUserService.addVideoUserInfo(videoUserModel);
    }

}
