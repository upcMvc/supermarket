package com.mvc.upc.service;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import java.io.*;

/**
 * Created by chenzifeng on 2017/7/8.
 */
@Service
public class Base64Service {
    private final Log log = LogFactory.getLog(this.getClass());
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

        File file = new File("image");
        if (!file.exists()){
            file.mkdir();
        }
        String path = "image/"+goodName+System.currentTimeMillis()+"."+suffix;
        log.debug("path:"+path);
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

    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     * @return
     */
    public  String getImageStr(String imgFile)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理

        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            System.out.println(in.available());
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        byte[] en = Base64.encodeBase64(data);
        return new String(en);//返回Base64编码过的字节数组字符串
    }


}
