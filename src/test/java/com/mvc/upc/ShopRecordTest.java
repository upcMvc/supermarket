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
public class ShopRecordTest {
    private int testUserId = 1;
    @Autowired
    private ShopRecordService shopRecordService;
    private Iterable<com.mvc.upc.model.ShopRecord> shopRecords;

    private final Log log = LogFactory.getLog(this.getClass());

    @Test
    public void shopRecordCreateTest() {
        for (int i = 2; i < 3; i++) {
            shopRecordService.createShopRecord(testUserId, i, i, i * i, i);
            log.info("创建一条shopRecord");
        }
    }

    @Test
    public void shopRecordSelectTest() {
        shopRecords = shopRecordService.findAllByStatusBetweenAndUserIdOrderByCreateTime(testUserId);
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
        shopRecords = shopRecordService.findAllByStatusBetweenAndUserIdOrderByCreateTime(testUserId);
        log.info("更新指定用户的所有订单");
        shopRecords.forEach(shopRecord -> shopRecord.setStatus(0));
        shopRecords.forEach(shopRecord -> shopRecordService.evalution(shopRecord.getId(), "this is just a test"));
    }

    @Test
    public void shopRecordDeleteTest() {
        shopRecords = shopRecordService.findAllByStatusBetweenAndUserIdOrderByCreateTime(testUserId);
        shopRecords.forEach(shopRecord -> shopRecordService.deleteShopRecord(shopRecord.getId()));
        log.info("删除指定用户的所有订单");

    }


}