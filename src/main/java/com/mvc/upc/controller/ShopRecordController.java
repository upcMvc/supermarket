package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.service.ShopRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jay on 7/8/2017.
 * 购买记录的增/删/查
 * <p>
 * api：
 * create：创建购买订单
 * delete：根据id删除购买记录（仅将状态标记为用户已删除）
 * find：返回用户的所有订单
 */
@RestController
@RequestMapping("/ShopRecord")
public class ShopRecordController {
    @Autowired
    private ShopRecordService shopRecordService;

    @RequestMapping("/create")
    public Object create(int userId, int goodId, String createTimem, int number, double cost, int wareHouseId, int status) {
        shopRecordService.createShopRecord(userId, goodId, createTimem, number, cost, wareHouseId, 2);
        return new JsonMes(1, "创建订单成功");
    }

    public Object delete(int id) {
        shopRecordService.deleteShopRecord(id);
        return new JsonMes(1, "删除订单成功");
    }

    public Object find(int userId) {
        return shopRecordService.findAllByUserIdAndStatusIsLessThanOrderByCreateTime(userId);
    }

}