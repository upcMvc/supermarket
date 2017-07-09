package com.mvc.upc.service;


import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by chenzifeng on 2017/7/8.
 */
public class Base64Service {

    /**
     * @Description: 将base64编码字符串转换为图片
     * @param imgStr base64编码字符串
     * @param goodName 商品名
     * @param  suffix 图片的后缀名
     * @return 生成文件的路径名
     */

    public  String  generateImage(String imgStr,String goodName,String suffix) {
        if(imgStr==null)
            return null;
        String path = "/image+/"+goodName+System.currentTimeMillis()+"."+suffix;
        File file = new File("image");
        if (!file.exists()){
            file.mkdir();
        }
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
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
