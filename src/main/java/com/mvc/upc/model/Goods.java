package com.mvc.upc.model;


import javax.persistence.*;

/**
 * Created by chenzifeng on 2017/7/8.
 * kinds 分类：日常用品，零食，水果，饮品
 */
@Entity
@Table(name="goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name; //商品名
    private String kinds; //商品分类
    private String imgPath;  //图片
    private String describes;  //商品描述
    private int num;
    private double price;

    public Goods(String name, String kinds, String imgPath, String describe, int num, double price) {
        this.name = name;
        this.kinds = kinds;
        this.imgPath = imgPath;
        this.describes = describe;
        this.num = num;
        this.price = price;
    }

    public Goods() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
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

    public int getId() {
        return id;
    }
}
