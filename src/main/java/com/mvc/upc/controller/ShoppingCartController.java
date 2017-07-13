package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.ShoppingCartToFront;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.model.Goods;
import com.mvc.upc.model.ShoppingCart;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.repository.ShoppingCartRepository;
import com.mvc.upc.service.GoodsService;
import com.mvc.upc.service.ShoppingCartService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @ApiOperation(value = "增加商品到购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "goodId", value = "商品id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "num", value = "数量", required = true, dataType = "int")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(int userId, int goodId, int num) {
        ShoppingCart shoppingCart = shoppingCartRepository.findFirstByGoodIdOrderByCreateTime(goodId);
        if (shoppingCart != null) {
            this.update(shoppingCart.getId(), 1);
        } else {
            shoppingCartService.createShoppingCart(userId, goodId, num);
        }
        return new JsonMes(1, "添加购物车成功");
    }


    @ApiOperation(value = "删除购物车中商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "id字段值", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(int id) {
        boolean ver = shoppingCartService.deleteShoppingCart(id);
        if (ver) {
            return new JsonMes(1, "删除购物车商品成功");
        } else {
            return new JsonMes(0, "删除购物车商品失败");
        }
    }


    @ApiOperation(value = "获取购物车中商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Object find(int userId) {
        List<ShoppingCart> shoppingCarts = shoppingCartService.findAllByUserIdOrderByCreateTime(userId);
        List<Goods> goods = new ArrayList<>();
        shoppingCarts.forEach(shoppingCart -> goods.add(goodsRepository.findOne(shoppingCart.getGoodId())));
        List<ShoppingCartToFront> shoppingCartToFronts = new ArrayList<>();
        for (int i = 0; i < goods.size(); i++) {
            ShoppingCartToFront shoppingCartToFront = new ShoppingCartToFront();
            shoppingCartToFront.setGoodId(goods.get(i).getId());
            shoppingCartToFront.setDescribes(goods.get(i).getDescribes());
            shoppingCartToFront.setImgPath(goods.get(i).getImgPath());
            shoppingCartToFront.setPrice(goods.get(i).getPrice());
            shoppingCartToFront.setName(goods.get(i).getName());
            shoppingCartToFront.setUserId(shoppingCarts.get(i).getUserId());
            shoppingCartToFront.setShoppingCartId(shoppingCarts.get(i).getId());
            shoppingCartToFront.setGoodNum(shoppingCarts.get(i).getNum());
            shoppingCartToFronts.add(shoppingCartToFront);
        }
        return shoppingCartToFronts;
    }


    @ApiOperation(value = "修改购物车中商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "id字段值", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "num", value = "数量", required = true, dataType = "int")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(int id, int addNum) {
        boolean ver = shoppingCartService.updateShoppingCart(id, addNum);
        if (ver) {
            return new JsonMes(1, "修改成功");
        } else {
            return new JsonMes(0, "修改失败");
        }
    }
}