package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.model.Blacklist;
import com.mvc.upc.repository.BlacklistRepository;
import com.mvc.upc.service.BlacklistService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenzifeng on 2017/7/8.
 * 所有接口都需要管理员权限
 */
@RequestMapping("/blacklist")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class BlacklistController {

    @Autowired
    BlacklistService blacklistService;


    @ApiOperation(value = "添加黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="userId",value = "用户Id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name ="reason",value = "加入原因",required = true,dataType = "String")
    })
    @PostMapping("/create")
    public Object createBlacklist(int userId, String reason){
        return blacklistService.create(userId,reason);
    }


    @ApiOperation(value = "移除黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query" , name = "userId",value = "用户Id",required = true,dataType = "int")
    })
    @PostMapping("/delete")
    public Object createBlacklist(int userId){
        blacklistService.delete(userId);
        return new JsonMes(1,"删除成功");
    }


    @ApiOperation(value = "展示黑名单")
    @ApiImplicitParam(paramType = "header", name = SwaggerParameter.Authorization, dataType = "String")
    @GetMapping("/findall")
    public Object findAll(){
        return blacklistService.findAll();
    }





}
