package com.mvc.upc.service;

import com.mvc.upc.model.ShoppingCart;
import com.mvc.upc.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jay on 7/8/2017.
 */
@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void createShoppingCart(int userId, int goodId, int num, String createTime) {
        ShoppingCart shoppingCart = new ShoppingCart(userId, goodId, num, createTime);
        shoppingCartRepository.save(shoppingCart);
    }

    public Iterable<ShoppingCart> findAllByUserIdOrderByCreateTime(int userId) {
        return shoppingCartRepository.findAllByUserIdOrderByCreateTime(userId);
    }

    public void deleteShoppingCart(int id) {
        shoppingCartRepository.delete(id);
    }

    public void updateShoppingCart(int id, int num, String createTime) {

        ShoppingCart shoppingCart = shoppingCartRepository.findFirstById(id);
        shoppingCart.setNum(num);
        shoppingCart.setCreateTime(createTime);
        shoppingCartRepository.save(shoppingCart);
    }
}
