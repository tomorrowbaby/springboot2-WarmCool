package com.wy.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 描述：名字生成工具
 * @author
 * @date 2020/2/29
 */

public class CreateNameUtil {

    public  static String createNameByDate(){
        StringBuilder name = new StringBuilder();

        LocalDateTime now = LocalDateTime.now();

        String nowTime = now.format(DateTimeFormatter.ISO_DATE_TIME).replace("-","").replace(":","").replace(".","");

        name.append(nowTime);

        Random rand = new Random() ;
        name.append(rand.nextInt(100));

        return name.toString();
    }
}
