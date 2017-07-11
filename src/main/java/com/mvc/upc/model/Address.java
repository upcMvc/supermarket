package com.mvc.upc.model;

import javax.persistence.*;

/**
 * Created by wanghaojun on 2017/7/8.
 */
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int userId;
    private String location;
    private Double longitude;//经度
    private Double latitude;//纬度
    private String city;


    public Address() {
    }

    public Address(int userId, String location, Double longitude, Double latitude, String city) {
        this.userId = userId;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
