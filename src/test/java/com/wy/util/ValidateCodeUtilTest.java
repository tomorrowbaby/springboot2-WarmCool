package com.wy.util;

import com.wy.utils.ValidateCodeUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * 验证码测试
 */

public class ValidateCodeUtilTest {

    @Test
    public void validateCodeTest(){

        ValidateCodeUtil validateCodeUtil = new ValidateCodeUtil(160,20,4,5);
        try {
            String path="D:/"+new Date().getTime()+".png";
            System.out.println(validateCodeUtil.getCode()+" >"+path);
            validateCodeUtil.write(path);
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
