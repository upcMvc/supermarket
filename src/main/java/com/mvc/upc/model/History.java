package com.mvc.upc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wanghaojun on 2017/7/12.
 * 浏览记录
 */
@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int goodId;
    private String goodName;
    private String imgPath;
    private double price;

    private int userId;

    private long time;

    public History() {
    }

    public History(int goodId, String goodName, String imgPath, double price, int userId, long time) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.imgPath = imgPath;
        this.price = price;
        this.userId = userId;
        this.time = time;
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

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
