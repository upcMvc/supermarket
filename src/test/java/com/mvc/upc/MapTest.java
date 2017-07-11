package com.mvc.upc;

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


    @Test
    public void locationTest(){
        locationService.setlocation();
    }
}
