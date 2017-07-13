package com.mvc.upc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by lylllcc on 2017/7/11.
 */
@Component
public class AppConfig {

    @Value("${app.config.server}")
    public String serveraddress;


    @Value("${app.config.front}")
    public String front;

}
