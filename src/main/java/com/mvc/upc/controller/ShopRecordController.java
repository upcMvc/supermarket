package com.mvc.upc.controller;


import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.model.ShopRecord;
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
 * evalution：进行评论
 */
@RestController
@RequestMapping("/ShopRecord")
public class ShopRecordController {
    @Autowired
    private ShopRecordService shopRecordService;

    @RequestMapping("/create")
    public Object create(int userId, int goodId, int number, double cost, int wareHouseId,String addressId) {
        ShopRecord shopRecord = shopRecordService.createShopRecord(userId, goodId, number, cost, wareHouseId, addressId);
        if (shopRecord!=null) {
            return new JsonMes(1, "创建订单成功");
        } else {
            return new JsonMes(0, "创建订单失败");
        }
    }

    @RequestMapping("/delete")
    public Object delete(int id) {
        boolean ver = shopRecordService.deleteShopRecord(id);
        if (ver) {
            return new JsonMes(1, "删除订单成功");
        } else {
            return new JsonMes(0, "删除订单失败");
        }
    }

    @RequestMapping("/find")
    public Object find(int userId) {
        return shopRecordService.findAllByUserIdAndStatusIsLessThanOrderByCreateTime(userId);
    }

    @RequestMapping("/evalution")
    public Object evalution(int id, String evalution) {
        boolean ver = shopRecordService.evalution(id, evalution);
        if (ver) {
            return new JsonMes(1, "评价成功");
        } else {
            return new JsonMes(0, "评价失败");
        }
    }
}

