package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.repository.CollectionRepository;
import com.mvc.upc.service.CollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Request;

/**
 * Created by jay on 7/8/2017.
 * 商品收藏的增/删/查
 * <p>
 * api：
 * create：新增商品收藏
 * delete：删除收藏的商品
 * find：返回用户收藏的所有商品
 */
@RestController
@RequestMapping("/Collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;


    @ApiOperation(value = "增加用户收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "goodId", value = "商品id", required = true, dataType = "int")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(int userId, int goodId) {
        collectionService.createCollection(userId, goodId);
        return new JsonMes(1, "收藏成功");
    }


    @ApiOperation(value = "删除用户收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "id",value = "id字段值",required = true,dataType = "int")
    })
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delete(int id) {
        boolean ver = collectionService.deleteCollection(id);
        if (ver) {
            return new JsonMes(1, "删除成功");
        }
        return new JsonMes(0, "删除失败");
    }


    @ApiOperation(value = "获取用户收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "用户id",required = true,dataType = "int")
    })
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public Object findAllByUserIdOrderByCreateTime(int userId) {
        return collectionService.findAllByUserIdOrderByCreateTime(userId);
    }
}
