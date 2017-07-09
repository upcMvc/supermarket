package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.model.Store;
import com.mvc.upc.repository.StoreRepository;
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

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object create(int goodid,int wareHouseId,int num){

        Store store = new Store(goodid,wareHouseId,num);
        storeRepository.save(store);
        return new  JsonMes(1,"创建成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object update(int storeId,int num){
        Store store = storeRepository.findOne(storeId);
        store.setGoodNum(num);
        storeRepository.save(store);

        return new JsonMes(1,"更改成功");
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delete(int storeId){

        Store store = storeRepository.findOne(storeId);
        storeRepository.delete(store);

        return new JsonMes(1,"删除成功");
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
