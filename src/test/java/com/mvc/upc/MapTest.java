package com.mvc.upc;

import com.mvc.upc.service.Base64Service;
import com.mvc.upc.service.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by chenzifeng on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketApplication.class)
@WebAppConfiguration
public class MapTest {
    @Autowired
    LocationService locationService;
    @Autowired
    Base64Service base64Service;

    @Test
    public void locationTest(){
        locationService.setlocation();
    }
    @Test
    public void base64(){
        String path = "image/可乐.PNG";
        String reader= base64Service.getImageStr(path);
        System.out.println(reader);
        base64Service.generateImage(reader,"可乐","png");
    }
}
