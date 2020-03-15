package com.wy.member;

import com.github.pagehelper.Page;
import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.dao.MemberDOMapper;
import com.wy.dao.PasswordDOMapper;
import com.wy.dataobject.MemberDO;
import com.wy.dataobject.PasswordDO;
import com.wy.service.MemberService;
import com.wy.service.model.MemberModel;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    MemberDOMapper memberDOMapper;

    @Autowired
    PasswordDOMapper passwordDOMapper;

    @Test
    public void contextLoads() {
    }

    /**
     * 查询会员所有数量
     */
    @Test
    public void testSelectAllMemberCount(){
        System.out.println(memberService.selectAllMemberCount());
    }

    /**
     * Mybatis分页测试
     */
    @Test
    public void testMybatisPage() throws BusinessException {

        //mapper测试
        Page<MemberDO> memberDOPage = memberDOMapper.selectPageListByMember(null,null,null,null,null);
        System.out.println(memberDOPage.getResult());

        CommonReturnPageInfo list = memberService.getMemberListAndPageInfo(1,10,null,"phone","desc",null,null);
        System.out.println(list.getData().size());
        System.out.println(memberDOPage.getResult());
    }


    /**
     * 插入返回id测试
     */
    @Test
    public void testReturnId() {
        MemberDO memberDO = new MemberDO();
        memberDO.setAddress("河东");
        memberDO.setUsername("狮吼");
        memberDO.setPhone("123");
        memberDO.setEmail("adada@qq.com");
        System.out.println(memberDO.getId());
    }


    @Test
    public void  testValidateMemberInfoById()  {

        Long a = new Long(3);
        try {
            memberService.validateMemberInfoIsExistById(a,"李白");
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFtpUpload(){
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        try {
            ftpClient.connect("10.0.0.3");
            ftpClient.login("ftpuser", "123456");

            File srcFile = new File("C:\\Users\\Administrator\\Desktop\\图片素材\\111.jpg");
            fis = new FileInputStream(srcFile);
            //设置上传目录
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            //设置文件类型（二进制）

            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile("333.jpg", fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }

    }


    /**
     * 会员修改测试
     */
    @Test
    public void testMemberUpdate() throws BusinessException {
        MemberModel memberModel = new MemberModel();
        memberService.updateMemberInfo(memberModel);
    }


    @Test
    public void testMemberSelect(){
        MemberDO memberDO = memberDOMapper.selectMemberInfoByUsername("admin");
        PasswordDO passwordDO = passwordDOMapper.selectByMemberId(memberDO.getId());
        System.out.println(passwordDO.getEncrptPassword());
    }

}
