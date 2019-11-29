package com.wy.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密工具类
 */

public class EncryptionUtil {

    public static String encrypt(String password){
        if (StringUtils.isEmpty(password)) {
            return null;
        }
         return new BCryptPasswordEncoder().encode(password);
    }
}
