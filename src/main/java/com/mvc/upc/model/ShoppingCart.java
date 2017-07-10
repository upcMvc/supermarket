package com.mvc.upc.model;

import javax.persistence.*;

/**
 * Created by jay on 7/8/2017.
 * 购物车数据表
 */
@Table(name = "shopping_cart")
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int goodId;
    private int num;
    private String createTime;

    /**
     * @param userId     用户id
     * @param goodId     商品id
     * @param num        购物车中商品的数目
     */
    public ShoppingCart(int userId, int goodId, int num) {
        this.userId = userId;
        this.goodId = goodId;
        this.num = num;
        this.createTime = "" + System.currentTimeMillis();
    }

    public ShoppingCart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime() {
        this.createTime = "" + System.currentTimeMillis();
    }
}
