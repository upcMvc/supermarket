package com.mvc.upc.model;

import javax.persistence.*;

/**
 * Created by jay on 7/8/2017.
 * 购物记录数据表
 */
@Table(name = "shop_record")
@Entity
public class ShopRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int goodId;
    private String evalution;//评价
    private String createTime;
    private int number;
    private Double cost;
    private int wareHouseId; //仓库Id
    private int status = -2;//初始化为-1,当为0派送中,-1订单失败,1已经签收但是未评价,2完成评价,3订单已被用户删除



    public ShopRecord(int userId, int goodId, String createTime, int number, Double cost, int wareHouseId, int status) {
        this.userId = userId;
        this.goodId = goodId;
        this.createTime = createTime;
        this.number = number;
        this.cost = cost;
        this.wareHouseId = wareHouseId;
        this.status = status;
    }

    public ShopRecord(int userId, int goodId, String evalution, String createTime, int number, Double cost, int wareHouseId, int status) {
        this.userId = userId;
        this.goodId = goodId;
        this.evalution = evalution;
        this.createTime = createTime;
        this.number = number;
        this.cost = cost;
        this.wareHouseId = wareHouseId;
        this.status = status;
    }

    public ShopRecord() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getEvalution() {
        return evalution;
    }

    public void setEvalution(String evalution) {
        this.evalution = evalution;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(int wareHouseId) {
        this.wareHouseId = wareHouseId;
    }
}
