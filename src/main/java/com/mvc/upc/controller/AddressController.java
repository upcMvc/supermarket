package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.service.AddressService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanghaojun on 2017/7/10.
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @ApiOperation(value = "添加地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="userId",value = "用户Id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name ="location",value = "地址",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="latitude",value = "纬度",required = true,dataType = "double"),
            @ApiImplicitParam(paramType = "query",name ="longitude",value = "经度",required = true,dataType = "double"),
            @ApiImplicitParam(paramType = "query",name ="city",value = "城市",required = true,dataType = "String")

    })
    @PostMapping("/create")
    public Object create(int userId,String location, double latitude, double longitude, String city){
        return addressService.create(userId,location,latitude,longitude,city);
    }

    @ApiOperation(value = "查找某用户所有地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="userId",value = "用户Id",required = true,dataType = "int"),
    })
    @GetMapping("/findbyuserid")
    public Object findByUserId(int userId){
        return addressService.findByUserId(userId);
    }

    @ApiOperation(value = "删除地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="addressId",value = "地址Id",required = true,dataType = "int"),
    })
    @PostMapping("/delete")
    public Object delete(int addressId){
         addressService.delete(addressId);
         return new JsonMes(1,"删除地址成功");
    }


    @ApiOperation(value = "更新地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="addressId",value = "地址Id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name ="location",value = "地址",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="latitude",value = "纬度",required = true,dataType = "double"),
            @ApiImplicitParam(paramType = "query",name ="longitude",value = "经度",required = true,dataType = "double"),
            @ApiImplicitParam(paramType = "query",name ="city",value = "城市",required = true,dataType = "String")

    })
    @PostMapping("/update")
    public Object update(int addressId,String location,double latitude, double longitude, String city){
        return addressService.update(addressId,location,latitude,longitude,city);
    }

    @ApiOperation(value = "查找某一地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="addressId",value = "地址Id",required = true,dataType = "int"),
    })
    @GetMapping("/findone")
    public Object findOne(int addressId){
        return addressService.findOne(addressId);
    }

}
