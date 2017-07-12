package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.repository.ShopRecordRepository;
import com.mvc.upc.repository.WareHouseRepository;
import com.mvc.upc.service.LocationService;
import com.mvc.upc.service.WareHouseService;
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by chenzifeng on 2017/7/11.
 */
@RestController
@RequestMapping("/warehouse")
@PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_WAREHOUSEADMIN'})")
public class WareHouseController {
    @Autowired
    WareHouseService wareHouseService;
    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    ShopRecordRepository shopRecordRepository;

    @PostMapping(value = "/create")
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

    @PostMapping(value = "/delete")
    @ApiOperation(value = "移除仓库")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name="id",value = "仓库的id",required = true,dataType = "int"),
    })
    public Object delete(int id){
        wareHouseService.delete(id);
        return new JsonMes(1,"删除成功");
    }


    @GetMapping("/cityWarehouse")
    @ApiOperation(value = "查找一座城市的仓库")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name="city",value = "仓库所在城市名",required = true,dataType = "String")
    })
    public Object cityWarehouse(String city){
        return wareHouseRepository.findAllByCity(city);
    }

    @GetMapping("/allWarehouse")
    @ApiOperation(value = "查找一座城市的仓库")
    @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String")
    public Object allWarehouse(){
        return wareHouseRepository.findAll();
    }

    @GetMapping("/getShopRecord")
    @ApiOperation("/查询一间仓库的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query", name="whid",value = "仓库id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name="status",value = "订单状态信息",required = true,dataType = "int")
    })
    public Object getShopRecord(int whid,int status){

        return shopRecordRepository.findAllByWareHouseIdAndStatusOrderByCreateTime(whid,status);
    }
}
