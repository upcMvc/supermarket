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
    private int goodSid;
    private String evalution;//评价
    private String createTime;
    private int number;
    private Double cost;
    private int wareHouseId; //仓库Id

    public ShopRecord(int userId, int goodSid, String evalution, String createTime, int number, Double cost, int wareHouseId) {
        this.userId = userId;
        this.goodSid = goodSid;
        this.evalution = evalution;
        this.createTime = createTime;
        this.number = number;
        this.cost = cost;
        this.wareHouseId = wareHouseId;
    }

    public ShopRecord() {
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

    public int getGoodSid() {
        return goodSid;
    }

    public void setGoodSid(int goodSid) {
        this.goodSid = goodSid;
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
