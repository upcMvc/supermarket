package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.model.Store;
import com.mvc.upc.repository.StoreRepository;
import com.mvc.upc.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

/**
 * Update by chenzifeng on 7/9/2017
 *
 */
@RequestMapping("/store")
@RestController
public class StoreController{

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreService storeService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object create(int goodid,int wareHouseId,int num){
        storeService.create(goodid,wareHouseId,num);
        return new  JsonMes(1,"创建成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object update(int storeId,int num){
        if(storeService.update(storeId,num))
            return new JsonMes(1,"更改成功");
        else
            return new JsonMes(0,"未找到该记录");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delete(int storeId){
        if (storeService.delete(storeId))
            return new JsonMes(1,"删除成功");
        else
            return new JsonMes(0,"未找到该记录");
    }

    @RequestMapping("/findGood")
    public Object findGood(int goodid){
        return storeRepository.findByGoodId(goodid);
    }

    @RequestMapping("/findWH")
    public Object findWH(int whId){
        return storeRepository.findByWareHouseId(whId);
    }
    @RequestMapping("/findAll")
    public Object findAll(){
        return storeRepository.findAll();
    }

}
