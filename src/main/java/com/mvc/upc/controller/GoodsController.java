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

        Goods good = new Goods(name,kind,path,describe,num,price);
        goodsRepository.save(good);
        return new JsonMes(1,"创建成功");
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Object update(int goodid,String name,String describe,int num,double price){

    return new JsonMes(1,"更新成功");
    }
}
