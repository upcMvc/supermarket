package com.mvc.upc.model;

import javax.persistence.*;

/**
 * Created by chenzifeng on 2017/7/8.
 */
@Entity
@Table(name = "warehouse")
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String location;//定位
    private String name;
    private int userId;

    public WareHouse(String location, String name) {
        this.location = location;
        this.name = name;
    }

    public WareHouse() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
