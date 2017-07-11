package com.mvc.upc;

import com.mvc.upc.service.ShopRecordService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jay on 7/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopRecord {
    private int testUserId = 12506;
    @Autowired
    private ShopRecordService shopRecordService;
    private Iterable<com.mvc.upc.model.ShopRecord> shopRecords;

    private final Log log = LogFactory.getLog(this.getClass());

    @Test
    public void shopRecordCreateTest() {
        for (int i = 1; i < 3; i++) {
            shopRecordService.createShopRecord(testUserId, i, i, i * i, i);
            log.info("创建一条shopRecord");
        }
    }

    @Test
    public void shopRecordSelectTest() {
        shopRecords = shopRecordService.findAllByUserIdAndStatusIsLessThanOrderByCreateTime(testUserId);
        log.info("展示指定用户的所有订单");
        shopRecords.forEach(shopRecord -> System.out.println("userId is " + shopRecord.getUserId() + ", goodId is "
                + shopRecord.getGoodId() + ", number of good is "
                + shopRecord.getNumber() + ", cost is "
                + shopRecord.getCost() + ", warehoused is "
                + shopRecord.getWareHouseId() + ", status is "
                + shopRecord.getStatus()));
    }

    @Test
    public void shopRecordUpdateTest() {
    }
}