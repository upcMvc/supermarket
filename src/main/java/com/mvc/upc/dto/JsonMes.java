package com.mvc.upc.dto;

/**
 * Created by chenzifeng on 2017/7/8.
 *
 * 默认状态 ：
 * 1：成功
 * 0：出现错误
 * -1：失败
 *
 * 其他根据接口返回由作者自己定义
 */
public class JsonMes {

    private int id;
    private String message;

    public JsonMes(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public JsonMes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
