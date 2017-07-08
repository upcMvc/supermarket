package com.mvc.upc.service;


import org.apache.commons.codec.binary.Base64;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by chenzifeng on 2017/7/8.
 */
public class Base64Service {

    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param imgStr base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     */

    public  boolean generateImage(String imgStr, String path) {
        if(imgStr==null)
            return false;

        try{
            byte[] bytes = Base64.decodeBase64(imgStr);
            for (int i = 0; i<bytes.length;++i){
                if (bytes[i]<0){
                    bytes[i] +=256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
