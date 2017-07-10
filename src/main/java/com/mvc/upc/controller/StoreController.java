package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.model.Goods;
import com.mvc.upc.model.Store;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.repository.StoreRepository;
import com.mvc.upc.service.StoreService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Update by chenzifeng on 7/9/2017
 *
 */
@RequestMapping("/store")
@RestController
@PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_WAREHOUSEADMIN'})")
public class StoreController{

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreService storeService;
    @Autowired
    GoodsRepository goodsRepository;


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ApiOperation(value = "创建仓库存储")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "goodid",value = "商品id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name = "wareHouseId",value = "仓库id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name = "num",value = "存储数量",required = true,dataType = "int")
    })
    public Object create(int goodid,int wareHouseId,int num){
        storeService.create(goodid,wareHouseId,num);
        return new  JsonMes(1,"创建成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "更新仓库存储")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "storeId",value = "存储id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name = "num",value = "存储数量",required = true,dataType = "int")
    })
    public Object update(int storeId,int num){
        if(storeService.update(storeId,num))
            return new JsonMes(1,"更改成功");
        else
            return new JsonMes(0,"未找到该记录");
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation(value = "删除仓库存储")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "storeId",value = "存储id",required = true,dataType = "int")
    })
    public Object delete(int storeId){
        if (storeService.delete(storeId))
            return new JsonMes(1,"删除成功");
        else
            return new JsonMes(0,"未找到该记录");
    }

    @RequestMapping("/findGood")
    @ApiOperation(value = "查看仓库货物存储")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "goodid",value = "商品id",required = true,dataType = "int")
    })
    public Object findGood(int goodid){
        return storeRepository.findByGoodId(goodid);
    }

    @RequestMapping("/findWH")
    @ApiOperation(value = "查询仓库物品", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "whId", value = "仓库ID", required = true,dataType = "int")})
    public Object findWH(int whId){

        return storeService.findWH(whId);
    }
    @RequestMapping("/findAll")
    public Object findAll(){
        return storeRepository.findAll();
    }

}
