package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jay on 7/8/2017.
 */
@RestController
@RequestMapping("ShoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/create")
    public Object create(int userId, int goodId, String createTime) {
        shoppingCartService.createShoppingCart(userId, goodId, createTime);
        return new JsonMes(1, "添加购物车成功");
    }

    @RequestMapping("/delete")
    public Object delete(int id) {
        shoppingCartService.deleteShoppingCart(id);
        return new JsonMes(1, "删除购物车成功");
    }

    @RequestMapping("/find")
    public Object find(int userId) {
        return shoppingCartService.findAllByUserIdOrderByCreateTime(userId);
    }

}
