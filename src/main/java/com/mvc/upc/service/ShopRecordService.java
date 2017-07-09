package com.mvc.upc.service;

import com.mvc.upc.model.ShopRecord;
import com.mvc.upc.repository.ShopRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jay on 7/8/2017.
 */
@Service
public class ShopRecordService {
    @Autowired
    private ShopRecordRepository shopRecordRepository;

    //由于评价在订单交易成功后才进行，所以在此不进行传值
    public void createShopRecord(int userId, int goodId, String createTimem, int number, double cost, int wareHouseId, int status) {
        ShopRecord shopRecord = new ShopRecord(userId, goodId, createTimem, number, cost, wareHouseId, status);
        shopRecordRepository.save(shopRecord);
    }

    //当订单处于完成状态时才可以删除（仅将状态标记为用户已删除）
    public void deleteShopRecord(int id) {
        ShopRecord shopRecord = shopRecordRepository.findFirstById(id);
        if (shopRecord.getStatus() >= 1) {
            shopRecord.setStatus(3);
        }
    }

    //查找用户订单（除已被用户删除的订单）
    public Iterable<ShopRecord> findAllByUserIdAndStatusIsLessThanOrderByCreateTime(int userId) {
        return shopRecordRepository.findAllByUserIdAndStatusIsLessThanOrderByCreateTime(userId, 3);
    }

}
