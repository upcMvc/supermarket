package com.mvc.upc.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.model.Blacklist;
import com.mvc.upc.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenzifeng on 2017/7/8.
 */
@RequestMapping("/black")
@RestController
public class BlacklistController {

    @Autowired
    BlacklistRepository blacklistRepository;


    /**
     * @param userId
     * @param reason
     * @return
     * */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object create(int userId,String reason){
        //添加一个角色认证

        Blacklist blacklist = new Blacklist(userId,reason);
        return new JsonMes(1,"成功");
    }
}
