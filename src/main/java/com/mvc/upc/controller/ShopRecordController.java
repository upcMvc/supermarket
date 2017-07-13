package com.mvc.upc.controller;


import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.model.ShopRecord;
import com.mvc.upc.service.ShopRecordService;
import com.mvc.upc.service.WareHouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jay on 7/8/2017.
 * 购买记录的增/删/查
 * <p>
 * api：
 * create：创建购买订单
 * delete：根据id删除购买记录（仅将状态标记为用户已删除）
 * find：返回用户的所有订单
 * evalution：进行评论
 */
@RestController
@RequestMapping("/ShopRecord")
public class ShopRecordController {
    @Autowired
    private ShopRecordService shopRecordService;
    @Autowired
    private WareHouseService wareHouseService;


    @ApiOperation(value = "生成订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "goodId", value = "商品id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "num", value = "数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "cost", value = "订单货款", required = true, dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "addressId", value = "addressId", required = true, dataType = "int")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(int userId, int goodId, int number, double cost, int addressId) {
        ShopRecord shopRecord = shopRecordService.createShopRecord(userId, goodId, number, cost, addressId);
        if (shopRecord != null) {
            return new JsonMes(1, "创建订单成功");
        } else {
            return new JsonMes(0, "创建订单失败");
        }
    }

    @ApiOperation(value = "删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "id字段值", required = true, dataType = "int")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(int id) {
        boolean ver = shopRecordService.deleteShopRecord(id);
        if (ver) {
            return new JsonMes(1, "删除订单成功");
        } else {
            return new JsonMes(0, "删除订单失败");
        }
    }


    @ApiOperation(value = "获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "int")
    })
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Object find(int userId) {
        return shopRecordService.findAllByStatusBetweenAndUserIdOrderByCreateTime(userId);
    }


    @ApiOperation(value = "获取关于指定商品的所有评价")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodId", value = "商品id", required = true, dataType = "int")
    })
    @RequestMapping(value = "/whSendEmail", method = RequestMethod.GET)
    public Object findByGoodId(int goodId) {
        return shopRecordService.findAllByGoodIdOrderByCreateTime(goodId);
    }


    @ApiOperation(value = "评价订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "id字段值", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "evalution", value = "评价", required = true, dataType = "String")
    })
    @RequestMapping(value = "/evalution", method = RequestMethod.POST)
    public Object evalution(int id, String evalution) {
        boolean ver = shopRecordService.evalution(id, evalution);
        if (ver) {
            return new JsonMes(1, "评价成功");
        } else {
            return new JsonMes(0, "评价失败");
        }
    }


    @ApiOperation(value = "发送email给仓库管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "int")
    })
    @RequestMapping(value = "/whSendEmail", method = RequestMethod.POST)
    public void whSendEmail(int userId) {
        wareHouseService.sendEmail(userId);
    }


}

