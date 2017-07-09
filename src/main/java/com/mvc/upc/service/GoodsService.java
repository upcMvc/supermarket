package com.mvc.upc.service;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.model.Goods;
import com.mvc.upc.repository.GoodsRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by chenzifeng on 2017/7/9.
 */
@Service
public class GoodsService {

    @Autowired
    private Base64Service base64Service;
    @Autowired
    private GoodsRepository goodsRepository;

    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * @param name  商品名
     * @param kind  商品所属类别
     * @param describe  商品简介
     * @param num  商品数量
     * @param price  商品单价
     * @param reader  base64转码后的图片
     * @param suffix  图片后缀名
     *
     * @return true:创建成功
     *         false:文件上传失败
     * */
    public boolean create(String name, String kind,String describe, int num, double price,String reader,String suffix){
        String path = base64Service.generateImage(reader,name,suffix);
        log.info("文件上传路径为："+path);
        if(path==null){
            log.info("文件上传失败");
            return false;
        }
        Goods good = new Goods(name,kind,path,describe,num,price);
        goodsRepository.save(good);
        log.info("good创建成功");
        return true;
    }

    /**
     * @param goodid 需更改商品的id
     * @param name   更改后商品的名字
     * @param describe  更改对商品的描述
     * @param num   更改后商品数量
     * @param price 更改后商品的单价
     * @return true:更改成功
     *         false:未找到对应商品
     * */
    public boolean update(int goodid,String name,String describe,int num,double price){
        Goods goods = goodsRepository.findOne(goodid);
        if (goods==null){
            log.info("未找到商品");
            return false;
        }
        log.debug(goods);
        goods.setName(name);
        goods.setName(describe);
        goods.setNum(num);
        goods.setPrice(price);
        goodsRepository.save(goods);
        return true;
    }
    /**
     * @param goodId
     * @return
     * */
    public boolean delete(int goodId){
        Goods good = goodsRepository.findOne(goodId);
        if (good==null){
            log.info("未找到商品");
            return false;
        }
        goodsRepository.delete(goodId);
        return true;
    }
}
