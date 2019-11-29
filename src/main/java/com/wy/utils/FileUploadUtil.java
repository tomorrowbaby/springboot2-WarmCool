package com.wy.utils;







import com.wy.common.error.BusinessException;
import com.wy.common.error.EmBusinessError;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


/**
 * 文件上传工具类
 */

public class FileUploadUtil {

    private static Logger logger = LogManager.getLogger(FileUploadUtil.class) ;

    public static String imageFileUpload(MultipartFile file,String ip,String ftpName,String ftpPassword) throws IOException, BusinessException {

            if (file == null) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
            }
            StringBuilder fileName = new StringBuilder();

            LocalDateTime now = LocalDateTime.now();

            String nowTime = now.format(DateTimeFormatter.ISO_DATE_TIME).replace("-","").replace(":","").replace(".","");

            fileName.append(nowTime);

            Random rand = new Random() ;
            fileName.append(rand.nextInt(100));

            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            fileName.append(suffix);

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            ftpClient.login(ftpName,ftpPassword );


            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            //设置文件类型（二进制）

            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile(fileName.toString(), file.getInputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new BusinessException(EmBusinessError.SERVER_CREATE_CONNECTION_ERROR);
        } finally {
            IOUtils.closeQuietly(file.getInputStream());
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new BusinessException(EmBusinessError.SERVER_CLOSE_CONNECTION_ERROR);
            }
        }
        return fileName.toString();
    }

}
