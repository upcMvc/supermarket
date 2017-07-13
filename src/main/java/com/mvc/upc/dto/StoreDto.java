package com.mvc.upc.dto;

/**
 * Created by chenzifeng on 2017/7/13.
 */
public class StoreDto {
    private String  goodName;
    private String  path;
    private int num;
    private double price;

    public StoreDto(String goodName, String path, int num, double price) {
        this.goodName = goodName;
        this.path = path;
        this.num = num;
        this.price = price;
    }

    public StoreDto() {
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
