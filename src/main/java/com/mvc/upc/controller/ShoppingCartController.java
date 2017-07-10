package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jay on 7/8/2017.
 * 购物车中商品的增/删/查/改
 * <p>
 * api:
 * create:创建购物车商品
 * delete:根据id删除购物车商品
 * find:返回用户购物车中所有商品
 * update:修改购物车中商品的个数并更新添加时间
 */
@RestController
@RequestMapping("ShoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/create")
    public Object create(int userId, int goodId, int num) {
        shoppingCartService.createShoppingCart(userId, goodId, num);
        return new JsonMes(1, "添加购物车成功");
    }

    @RequestMapping("/delete")
    public Object delete(int id) {
        boolean ver = shoppingCartService.deleteShoppingCart(id);
        if (ver) {
            return new JsonMes(1, "删除购物车商品成功");
        } else {
            return new JsonMes(0, "删除购物车商品失败");
        }
    }

    @RequestMapping("/find")
    public Object find(int userId) {
        return shoppingCartService.findAllByUserIdOrderByCreateTime(userId);
    }

    @RequestMapping("/update")
    public Object update(int id, int num) {
        boolean ver = shoppingCartService.updateShoppingCart(id, num);
        if (ver) {
            return new JsonMes(1, "修改成功");
        } else {
            return new JsonMes(0, "修改失败");
        }
    }
}