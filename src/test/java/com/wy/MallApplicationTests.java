package com.wy;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wy.common.error.BusinessException;
import com.wy.common.response.CommonReturnPageInfo;
import com.wy.config.ServerUrlConfig;
import com.wy.dao.MemberDOMapper;
import com.wy.dataobject.MemberDO;
import com.wy.service.MemberService;
import com.wy.service.impl.MemberServiceImpl;
import com.wy.service.model.MemberModel;
import com.wy.utils.FileUploadUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {
}
