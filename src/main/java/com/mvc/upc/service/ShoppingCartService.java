package com.mvc.upc.service;

import com.mvc.upc.model.ShoppingCart;
import com.mvc.upc.repository.ShoppingCartRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jay on 7/8/2017.
 */
@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * @param userId
     * @param goodId
     * @param num
     */
    public ShoppingCart createShoppingCart(int userId, int goodId, int num) {
        ShoppingCart shoppingCart = new ShoppingCart(userId, goodId, num);
        return shoppingCartRepository.save(shoppingCart);
    }

    /**
     * @param userId
     * @return an Iterator
     */
    public List<ShoppingCart> findAllByUserIdOrderByCreateTime(int userId) {
        return shoppingCartRepository.findAllByUserIdOrderByCreateTime(userId);
    }

    /**
     * @param id
     */
    public boolean deleteShoppingCart(int id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findFirstById(id);
        if (shoppingCart != null) {
            shoppingCartRepository.delete(id);
            return true;
        } else {
            log.info("没有该购物车商品，无法删除");
            return false;
        }
    }

    /**
     * @param id
     * @param changeNum 增加/减少的数目
     */
    public boolean updateShoppingCart(int id, int changeNum) {
        try {
            ShoppingCart shoppingCart = shoppingCartRepository.findFirstById(id);
            shoppingCart.setNum(shoppingCart.getNum() + changeNum);
            if (shoppingCart.getNum() == 0) {
                this.deleteShoppingCart(id);
            } else {
                shoppingCart.setCreateTime();
                shoppingCartRepository.save(shoppingCart);
            }
            return true;
        } catch (Exception e) {
            log.info("更新购物车失败");
            return false;
        }
    }
}
