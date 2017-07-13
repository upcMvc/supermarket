package com.mvc.upc.dto;

/**
 * Created by jay on 7/13/2017.
 */
public class EvaluateByUsername {
    private int userid;
    private String evaluate;
    private String username;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
