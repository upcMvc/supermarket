package com.mvc.upc;

import com.mvc.upc.model.ShoppingCart;
import com.mvc.upc.service.ShoppingCartService;
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
public class ShoppingCartTest {
    private int testUserId=12506;
    @Autowired
    private ShoppingCartService shoppingCartService;
    private Iterable<ShoppingCart> shoppingCarts;
    private final Log log = LogFactory.getLog(this.getClass());


    @Test
    public void shoppingCartCreateTest() {
        for (int i = 0; i < 10; i++) {
            shoppingCartService.createShoppingCart(testUserId, i, i);
            log.info("插入一条购物车记录");
        }
    }

    @Test
    public void shoppingCartSelectTest() {
        shoppingCarts = shoppingCartService.findAllByUserIdOrderByCreateTime(testUserId);
        log.info("展示指定用户的所有购物车商品");
        shoppingCarts.forEach(shoppingCart ->
                System.out.println("userId is " + shoppingCart.getUserId() + ", "
                        + "goodId is " + shoppingCart.getGoodId() + ", "
                        + "num of good is " + shoppingCart.getNum()));
    }

    @Test
    public void shoppingCartUpdateTest() {
        shoppingCarts = shoppingCartService.findAllByUserIdOrderByCreateTime(testUserId);
        log.info("修改指定用户所有购物车商品");
        shoppingCarts.forEach(shoppingCart -> shoppingCart.setUserId(83820));
        shoppingCarts.forEach(shoppingCart -> shoppingCart.setGoodId(11));
        shoppingCarts.forEach(shoppingCart -> shoppingCart.setNum(100));
        shoppingCarts.forEach(ShoppingCart::setCreateTime);
    }

    @Test
    public void shoppingCartDeleteTest() {
        shoppingCarts = shoppingCartService.findAllByUserIdOrderByCreateTime(testUserId);
        log.info("\n根据id逐条删除指定用户所有购物车");
        shoppingCarts.forEach(shoppingCart ->
                shoppingCartService.deleteShoppingCart(shoppingCart.getId()));
    }
}
