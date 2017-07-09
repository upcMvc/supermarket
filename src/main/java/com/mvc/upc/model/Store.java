package com.mvc.upc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Update by chenzifeng on 7/9/2017.
 * 商品存储数据表
 * goodId:存储在商店的商品ID
 * wareHouserId:
 */
@Table(name = "store")
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int goodId;
    private int wareHouseId;
    int num;

    public Store(int goodId, int wareHouseId,int num) {
        this.goodId = goodId;
        this.wareHouseId = wareHouseId;
        this.num = num;
    }

    public Store() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(int wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
