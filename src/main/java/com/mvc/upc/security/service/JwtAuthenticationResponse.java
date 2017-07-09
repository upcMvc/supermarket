package com.mvc.upc.security.service;

import java.io.Serializable;

/**
 * Created by lylllcc on 2017/7/9.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}