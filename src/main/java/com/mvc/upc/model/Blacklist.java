package com.mvc.upc.model;

import javax.persistence.*;
/**
 * Created by chenzifeng on 2017/7/8.
 */
@Entity
@Table( name="blacklist")
public class Blacklist {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int id;

    private int userId;
    long createTime;
    String reason;

    public Blacklist(int userId, String reason) {
        this.userId = userId;
        this.createTime = System.currentTimeMillis();
        this.reason = reason;
    }

    public Blacklist() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId() {
        return id;
    }
}
