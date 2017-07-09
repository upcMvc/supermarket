package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.repository.CollectionRepository;
import com.mvc.upc.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jay on 7/8/2017.
 */
@RestController
@RequestMapping("/Collection")
public class CollectionController {
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    CollectionService collectionService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(int userId, int goodId, String createTime) {
        collectionService.createCollection(userId, goodId, createTime);
        return new JsonMes(1, "收藏成功");
    }
}
