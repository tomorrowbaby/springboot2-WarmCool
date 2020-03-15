package com.wy.service.manager;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.wy.utils.CreateNameUtil;
import com.wy.utils.FileUploadUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 描述：七牛云sdk集成文件上传
 * @author
 * @date 2020/2/29
 */

public class QiNiu {


    private static final String QINIUSERVER = "http://q6g3wzhaj.bkt.clouddn.com/";
    private static Logger logger = LogManager.getLogger(FileUploadUtil.class) ;

    public static String upload(MultipartFile file){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "nQUpizuWEUvnjnydC65BZlCrKQzPjmEMU2zg6T5z";
        String secretKey = "2dJnjkfXL63QRkdtV07fsZPlVRniLKVsF0_bkykc";
        String bucket = "wangyuimage";

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = CreateNameUtil.createNameByDate();

        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            e.printStackTrace();
        }
        return QINIUSERVER+key;

    }

}
