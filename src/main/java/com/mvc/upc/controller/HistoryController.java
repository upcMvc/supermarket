package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.model.History;
import com.mvc.upc.service.HistoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanghaojun on 2017/7/12.
 */
@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "添加一条历史记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="goodId",value = "商品Id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name ="userId",value = "用户Id",required = true,dataType = "int")
    })
    @PostMapping("/create")
    public Object create(int goodId, int userId){
        return historyService.create(goodId,userId);
    }

    @ApiOperation(value = "删除一条历史记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="historyId",value = "历史记录Id",required = true,dataType = "int"),
    })
    @PostMapping("/delete")
    public Object delete(int historyId){
        historyService.delete(historyId);
        return new JsonMes(1,"删除历史记录成功");
    }

    @ApiOperation(value = "查找用户历史记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="userId",value = "用户Id",required = true,dataType = "int")
    })
    @GetMapping("/find")
    public Object find(int userId){
        return historyService.findAllByUserId(userId);
    }
}
