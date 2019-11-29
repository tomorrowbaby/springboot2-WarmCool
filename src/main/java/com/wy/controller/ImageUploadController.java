package com.wy.controller;

import com.sun.deploy.net.HttpResponse;
import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import com.wy.common.response.CommonReturnType;
import com.wy.common.response.KindeditorImageReturnType;
import com.wy.config.ServerUrlConfig;
import com.wy.utils.FileUploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 描述：图片上传控制层
 * @author wangyu
 * @date 2019/8/23
 */

@Controller
@RequestMapping(value = "/image")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ImageUploadController {

    /**
     * webupload对接图片上传
     */
    @RequestMapping(value = "/webupload",method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType webuploadImage(MultipartFile file) throws IOException, BusinessException {
        if(file == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        String imageName = null;
        try {
            imageName = FileUploadUtil.imageFileUpload(file, ServerUrlConfig.ImageFileServerIP,ServerUrlConfig.FtpName,ServerUrlConfig.FtpPassword);
        }catch (Exception e) {
            imageName = null;
        }
        if (imageName == null) {
            return CommonReturnType.create("图片上传功能暂时出现问题，请联系管理员！","fail");
        }
        return CommonReturnType.create(imageName);
    }

    /**
     * kindeditor编辑器图片上传
     */
    @RequestMapping(value = "/kindeditor",method = RequestMethod.POST)
    public String kindeditorImageUpload(@RequestParam String callBackPath , @RequestParam("imgFile") MultipartFile file, HttpServletResponse response) throws IOException, BusinessException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

       if (file == null) {
           throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
       }

       String fileName  ;
        try{
            fileName = FileUploadUtil.imageFileUpload(file,ServerUrlConfig.ImageFileServerIP,ServerUrlConfig.FtpName,ServerUrlConfig.FtpPassword);
        }catch (Exception e) {
            e.printStackTrace();
            fileName = "fail";
        }
        KindeditorImageReturnType kindeditorImageReturnType = new KindeditorImageReturnType();

        //上传失败
        if (StringUtils.equals(fileName,"fail")) {

            //跨域需要进行重定向
            return "redirect:" + callBackPath + "?error=1&message="+ URLEncoder.encode("图片功能出现异常，请联系管理员错误信息", "UTF-8");
        }

        kindeditorImageReturnType.setError(0);
        kindeditorImageReturnType.setUrl("http://"+ServerUrlConfig.ImageFileServerIP+"/"+fileName);
        String returnStr = "redirect:" + callBackPath + "?error="+kindeditorImageReturnType.getError()+"&url="+ kindeditorImageReturnType.getUrl();
        return returnStr;
    }

}
