package com.mvc.upc;

import com.mvc.upc.util.MailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by lylllcc on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketApplication.class)
@WebAppConfiguration
public class Mailtest {

    @Test
    public void testMail(){
        MailUtils.send("710801583@qq.com","部署！！！");
    }
}
