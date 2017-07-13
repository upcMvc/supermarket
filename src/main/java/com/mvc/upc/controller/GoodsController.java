package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.model.Goods;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.service.Base64Service;
import com.mvc.upc.service.FileUploadService;
import com.mvc.upc.service.GoodsService;
import com.mvc.upc.storage.FileSystemStorageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

/**
 * Created by chenzifeng on 2017/7/8.
 * 关于商品的 增·删·查·改 必须由相应的仓库管理员进行操作
 * 接口 ：
 * /goods/create
 * 参数：String name, String kind,String describe,
 * int num, double price,String reader,String suffix
 * 其中 reader是图片经过base64转码后的字符串，suffix是图片的后缀名
 * <p>
 * /goods/update
 * <p>
 * /goods/findAll
 * <p>
 * /goods/delete
 */
@RequestMapping("/goods")
@RestController
public class GoodsController {

    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodsService goodsService;
    @Autowired
    Base64Service base64Service;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    FileSystemStorageService fss;

    @PostMapping(value = "/create")
    @ApiOperation(value = "添加顾客所见商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="name",value = "商品名",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="kind",value = "商品种类",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="describe",value = "商品描述",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="num",value = "商品数量",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query" ,name ="price",value = "商品单价",required = true,dataType = "double"),
            @ApiImplicitParam(paramType = "query" ,name ="reader",value = "图片base64转码",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="suffix",value = "图片后缀名",required = true,dataType = "String")
    })
    public Object create(String name, String kind, String describe, int num, double price, String reader, String suffix) {
        if (goodsService.create(name, kind, describe, num, price, reader, suffix))
            return new JsonMes(1, "创建成功");
        else
            return new JsonMes(-1, "创建失败");
    }


    @PostMapping(value = "/update")
    @ApiOperation(value = "更新客户所见的商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="goodid",value = "商品id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query" ,name ="name",value = "商品名",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="describe",value = "商品描述",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="num",value = "商品数量",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query" ,name ="price",value = "商品单价",required = true,dataType = "double")
    })
    public Object update(int goodid, String name, String describe, int num, double price) {
        if(goodsService.update(goodid,name,describe,num,price))
            return new JsonMes(1, "更新成功");
        else
            return new JsonMes(0,"未找到该商品");
    }

    @GetMapping("/findAll")
    public Object findAll() {
        return goodsRepository.findAll();
    }


    @PostMapping(value = "/delete")
    @ApiOperation(value = "更新客户所见的商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="goodid",value = "商品id",required = true,dataType = "int") })
    public Object delete(int goodId) {
        if (goodsService.delete(goodId))
            return new JsonMes(1, "删除成功");
        else
            return new JsonMes(0,"未找到该商品");
    }

    @PostMapping("/getImage")
    @ApiOperation(value = "返给图片的base64编码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="imgPath",value = "文件路径",required = true,dataType = "String") })
    public String getImage(String imgPath){
        return base64Service.getImageStr(imgPath);
    }

//    @PostMapping("/test")
    @PostMapping("/newCreate")
    @ApiOperation(value = "添加顾客所见商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="name",value = "商品名",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="kind",value = "商品种类",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="describe",value = "商品描述",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="num",value = "商品数量",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query" ,name ="price",value = "商品单价",required = true,dataType = "double"),
            @ApiImplicitParam(paramType = "query" ,name ="file",value = "图片",required = true,dataType = "MultipartFile"),
    })
    public Object  newCreate(String name, String kind, String describe, int num, double price, MultipartFile file){

        return goodsService.newCreate(name,kind,describe,num,price,file);

    }

}
