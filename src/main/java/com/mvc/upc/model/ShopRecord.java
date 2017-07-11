package com.mvc.upc.model;

import com.mvc.upc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * Created by jay on 7/8/2017.
 * 购物记录数据表
 */
@Table(name = "shop_record")
@Entity
public class ShopRecord {
    @Autowired
    LocationService locationService;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int goodId;
    private String evalution;//评价
    private String createTime;
    private int number;
    private double cost;
    private int wareHouseId; //仓库Id
    private int status = -2;//初始化为-2,当为0派送中,-1订单失败,1已经签收但是未评价,2完成评价,3订单已被用户删除
    private String addressId;

    /**
     * @param userId      用户id
     * @param goodId      商品id
     * @param number      该商品的购买数量
     * @param cost        该购买记录的花费
     * @param wareHouseId 商品发货仓库
     * @param addressId   送货地址id
     */
    public ShopRecord(int userId, int goodId, int number, double cost, String addressId) {
        this.userId = userId;
        this.goodId = goodId;
        this.number = number;
        this.createTime = "" + System.currentTimeMillis();
        this.cost = cost;
        this.wareHouseId = locationService.compareCoordinate(addressId);
        this.status = 2;
        this.addressId = addressId;
    }

    public ShopRecord() {
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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

    public void setCreateTime() {
        this.createTime = "" + System.currentTimeMillis();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(int wareHouseId) {
        this.wareHouseId = wareHouseId;
    }
}
