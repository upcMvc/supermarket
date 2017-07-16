package com.mvc.upc.dto;

/**
 * Created by chenzifeng on 2017/7/14.
 */
public class GoodDto {
   private String goodName;
   private int num;

    public GoodDto(String goodName, int num) {
        this.goodName = goodName;
        this.num = num;
    }

    public GoodDto() {
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
