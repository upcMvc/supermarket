package com.mvc.upc;

import com.mvc.upc.service.BlacklistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.*;

/**
 * Created by wanghaojun on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketApplication.class)
@WebAppConfiguration
public class BlacklistTest {
    @Autowired
    BlacklistService blacklistService;

    @Test
    public void isBlacklistTest(){
        System.out.println(blacklistService.isBlacklist(1));
    }

}
