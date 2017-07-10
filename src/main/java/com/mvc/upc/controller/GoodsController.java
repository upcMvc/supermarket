package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.model.Goods;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.service.Base64Service;
import com.mvc.upc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.rmi.runtime.Log;

/**
 * Created by chenzifeng on 2017/7/8.
 * 关于商品的 增·删·查·改 必须由相应的仓库管理员进行操作
 * 接口 ：
 * /goods/create
 * 参数：String name, String kind,String describe,
 * int num, double price,String reader,String suffix
 * 其中 reader是图片经过base64转码后的字符串，suffix是图片的后缀名
 * <p>
 * /goods/update
 * <p>
 * /goods/findAll
 * <p>
 * /goods/delete
 */
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(String name, String kind, String describe, int num, double price, String reader, String suffix) {
        if (goodsService.create(name, kind, describe, num, price, reader, suffix))
            return new JsonMes(1, "创建成功");
        else
            return new JsonMes(-1, "创建失败");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(int goodid, String name, String describe, int num, double price) {
        if(goodsService.update(goodid,name,describe,num,price))
            return new JsonMes(1, "更新成功");
        else
            return new JsonMes(0,"未找到该商品");
    }

    @RequestMapping("/findAll")
    public Object findAll() {
        return goodsRepository.findAll();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delete(int goodId) {

        return new JsonMes(1, "删除成功");
    }
}
