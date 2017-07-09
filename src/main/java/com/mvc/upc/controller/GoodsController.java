package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.model.Goods;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.service.Base64Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenzifeng on 2017/7/8.
 * 关于商品的 增·删·查·改
 * 接口 ：
 *  /goods/create
 *  参数：String name, String kind,String describe,
 *       int num, double price,String reader,String suffix
 *       其中 reader是图片经过base64转码后的字符串，suffix是图片的后缀名
 *
 *  /goods/update
 *
 *  /goods/findAll
 *
 *  /goods/delete
 */
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    Base64Service base64Service;
    @Autowired
    GoodsRepository goodsRepository;

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Object create(String name, String kind,String describe, int num, double price,String reader,String suffix){

        String path = base64Service.generateImage(reader,name,suffix);
        if(path==null)
            return new JsonMes(-1,"失败");
        Goods good = new Goods(name,kind,path,describe,num,price);
        goodsRepository.save(good);
        return new JsonMes(1,"创建成功");
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Object update(int goodid,String name,String describe,int num,double price){
        Goods goods = goodsRepository.findOne(goodid);
        goods.setName(name);
        goods.setName(describe);
        goods.setNum(num);
        goods.setPrice(price);
        goodsRepository.save(goods);
    return new JsonMes(1,"更新成功");
    }

    @RequestMapping("/findAll")
    public Object findAll(){
        return goodsRepository.findAll();
    }

    @RequestMapping("/delete")
    public Object delete(int goodId){
        Goods good = goodsRepository.findOne(goodId);

        goodsRepository.delete(goodId);
        return new JsonMes(1,"删除成功");
    }
}
