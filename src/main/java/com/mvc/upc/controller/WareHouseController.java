package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.repository.WareHouseRepository;
import com.mvc.upc.service.LocationService;
import com.mvc.upc.service.WareHouseService;
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenzifeng on 2017/7/11.
 */
@RestController
@RequestMapping("/warehouse")
public class WareHouseController {
    @Autowired
    WareHouseService wareHouseService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object create(String name,int userId,String city,String coordinate,String location){
        wareHouseService.create(location,name,userId,city,coordinate);
        return new JsonMes(1,"创建成功");
    }


}
