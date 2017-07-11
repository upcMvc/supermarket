package com.mvc.upc.service;

import com.mvc.upc.model.ShopRecord;
import com.mvc.upc.repository.ShopRecordRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jay on 7/8/2017.
 */
@Service
public class ShopRecordService {
    @Autowired
    private ShopRecordRepository shopRecordRepository;
    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * 由于评价在订单交易成功后才进行，所以在此不进行传值
     */
    public ShopRecord createShopRecord(int userId, int goodId, int number, double cost, int addressId) {
        ShopRecord shopRecord = new ShopRecord(userId, goodId, number, cost, addressId);
        return shopRecordRepository.save(shopRecord);
    }

    /**
     * 当订单处于完成状态时才可以删除（仅将状态）
     *
     * @param id
     */
    public boolean deleteShopRecord(int id) {
        ShopRecord shopRecord = shopRecordRepository.findFirstById(id);
        if (shopRecord != null) {
            if (shopRecord.getStatus() >= 1) {
                shopRecord.setStatus(3);
            }
            return true;
        } else {
            log.info("找不到相应的订单");
            return false;
        }

    }

    /**
     * 查找用户订单（除已被用户删除的订单）
     *
     * @param userId
     * @return an Iterator
     */
    public Iterable<ShopRecord> findAllByUserIdAndStatusIsLessThanOrderByCreateTime(int userId) {
        Iterable<ShopRecord> shopRecords = shopRecordRepository.findAllByUserIdAndStatusIsLessThanOrderByCreateTime(userId, 3);
        if (shopRecords != null) {
            log.info("没有存在的订单");
        }
        return shopRecords;
    }

    /**
     * 订单完成后进行评论的接口
     *
     * @param id        订单id
     * @param evalution 用户评论
     */
    public boolean evalution(int id, String evalution) {
        ShopRecord shopRecord = shopRecordRepository.findFirstById(id);
        if (shopRecord != null) {
            shopRecord.setEvalution(evalution);
            shopRecord.setStatus(2);
            shopRecordRepository.save(shopRecord);
            return true;
        } else {
            log.info("找不到相应的订单，无法评论");
            return false;
        }
    }
}
