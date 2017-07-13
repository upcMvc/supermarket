package com.mvc.upc.controller;

import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.model.Goods;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.storage.StorageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lylllcc on 2017/4/17.
 */

@RestController
public class FileController {

    private final StorageService storageService;
    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    FileController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> showFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(file);
    }

    @GetMapping("/getGoodImage")
    @ApiOperation("获取商品图片路径")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = SwaggerParameter.Authorization,dataType = "String"),
            @ApiImplicitParam(paramType = "query" ,name ="goodId",value = "商品id",required = true,dataType = "int")
    })
    public String getGoodImage(int goodId){
        Goods goods = goodsRepository.findOne(goodId);
        return goods.getImgPath();
    }
}
