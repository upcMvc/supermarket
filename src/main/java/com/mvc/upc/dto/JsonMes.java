package com.mvc.upc.dto;

/**
 * Created by chenzifeng on 2017/7/8.
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
