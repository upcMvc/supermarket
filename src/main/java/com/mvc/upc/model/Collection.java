package com.mvc.upc.model;

import javax.persistence.*;

/**
 * Created by jay on 7/8/2017.
 * 收藏数据表
 */
@Table(name = "collection")
@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int goodId;
    private String createTime;

    /**
     * @param userId 用户id
     * @param goodId 商品id
     */

    public Collection(int userId, int goodId) {
        this.userId = userId;
        this.goodId = goodId;
        this.createTime = "" + System.currentTimeMillis();
    }

    public Collection() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime() {
        this.createTime = "" + System.currentTimeMillis();
    }
}
