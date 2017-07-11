package com.mvc.upc;

import com.mvc.upc.model.Collection;
import com.mvc.upc.service.CollectionService;
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
public class CollectionTest {
    private int testUserId;
    @Autowired
    private CollectionService collectionService;
    private Iterable<Collection> collections;

    private final Log log = LogFactory.getLog(this.getClass());

    @Test
    public void collectionCreateTest() {
        for (int i = 0; i < 10; i++) {
            collectionService.createCollection(testUserId, i);
            log.info("\n插入一条collection");
        }
    }

    @Test
    public void collectionSelectTest() {
        collections = collectionService.findAllByUserIdOrderByCreateTime(testUserId);
        log.info("\n展示指定用户所有collection");
        collections.forEach(collection ->
                System.out.println("userId is " + collection.getUserId() + ", "
                        + "goodId is " + collection.getGoodId() + ", "
                        + "createTime is " + collection.getCreateTime()));
    }

    @Test
    public void collectionDeleteTest() {
        collections = collectionService.findAllByUserIdOrderByCreateTime(testUserId);
        log.info("\n根据id逐条删除指定用户所有收藏");
        collections.forEach(collection -> collectionService.deleteCollection(collection.getId()));
    }

}
