package com.mvc.upc.service;

import com.mvc.upc.model.WareHouse;
import com.mvc.upc.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanghaojun on 2017/7/9.
 */
@Service
public class WareHouseService {

    @Autowired
    WareHouseRepository wareHouseRepository;

    /**
     * 创建一个仓库
     * @param location
     * @param name
     * @param userId
     * @return
     */
    public WareHouse create(String location,String name,int userId){
        WareHouse wareHouse=new WareHouse();
        wareHouse.setLocation(location);
        wareHouse.setName(name);
        wareHouse.setUserId(userId);
        return wareHouseRepository.save(wareHouse);
    }

    /**
     * 删除一个仓库
     * @param id
     */
    public void delete(int id){
        wareHouseRepository.delete(id);
    }


    /**
     * 查找所有仓库
     * @return
     */
    public Iterable<WareHouse> findAll(){
        return wareHouseRepository.findAll();
    }

    /**
     * 根据仓库管理员的userId查找仓库
     * @param userId
     * @return
     */
    public WareHouse findByUserId(int userId){
        return wareHouseRepository.findFirstByUserId(userId);
    }

    /**
     * 更新仓库信息
     * @param id
     * @param location
     * @param name
     * @param userId
     * @return
     */
    public WareHouse update(int id,String location,String name,int userId){
        WareHouse wareHouse=wareHouseRepository.findOne(id);
        wareHouse.setUserId(userId);
        wareHouse.setName(name);
        wareHouse.setLocation(location);
        return wareHouseRepository.save(wareHouse);
    }

    /**
     * 更换仓库管理员
     * @param id
     * @param userId
     * @return
     */
    public WareHouse changeUser(int id,int userId){
        WareHouse wareHouse=wareHouseRepository.findOne(id);
        wareHouse.setUserId(userId);
        return wareHouseRepository.save(wareHouse);
    }



}
