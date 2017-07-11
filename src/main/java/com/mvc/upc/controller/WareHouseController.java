package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.repository.WareHouseRepository;
import com.mvc.upc.service.LocationService;
import com.mvc.upc.service.WareHouseService;
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "新建仓库")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name="name",value = "仓库名",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name="userId",value = "仓库管理员的用户id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query", name="city",value = "仓库所在城市名",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name="coordinate",value = "仓库坐标",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name="location",value = "仓库地理位置",required = true,dataType = "String"),
    })
    public Object create(String name,int userId,String city,String coordinate,String location){
        wareHouseService.create(location,name,userId,city,coordinate);
        return new JsonMes(1,"创建成功");
    }


}
