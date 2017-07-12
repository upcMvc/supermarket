package com.mvc.upc;

import com.mvc.upc.model.Goods;
import com.mvc.upc.model.Store;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.repository.StoreRepository;
import com.mvc.upc.service.Base64Service;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.management.openmbean.SimpleType;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenzifeng on 2017/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketApplication.class)
@WebAppConfiguration
public class Good_StoreTest {


    @Autowired
    private Base64Service base64Service;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private StoreRepository storeRepository;

    private final Log log = LogFactory.getLog(this.getClass());

    @Test
    public void create() {
        String[] name = {"可乐", "雪碧", "橙汁", "啤酒", "果粒橙", "牛奶", "矿泉水"};
        for (int i = 0; i < 7; i++) {

            String kind = "饮品";
            String describe = "很爽很好喝";
            int num = 1000;
            double price = 3.0;
            String reader = "iVBORw0KGgoAAAANSUhEUgAAAe8AAAFPCAYAAABklUYjAAAgAElEQVR4nOy9B5Qcx3U2+nX3hJ3NAYsciUAEAmBAIAHmTDCJmRIpKlCmJFqWf9k+/u2Xjs97x+fX8/N5v23ZlmTJkp5kBYpJYhBzAkESJHLOcbFYAJvTpE6v7q2q2Z7eCHDBpL44hdmZ7q5wq7purHuNcxdd7GMEYI7oLsAI32d4I6rPM+TnCJuhGoqbgcOfll/cnmsUP+WL74ZhFJ73/aFbNHxzyOsfBqgfuvXh+vFpAonfwSGTycC2bSSTSXjewOtj6Br6r5O++weuT8Nw69gbpGH9XN91vS68Ae/7Y4fh8BhBBBF8OIgNRDSG23wjiOBMQK+1eDzOaywWi/VjWvTaM0LPhK+HmR6jcL34/vDzVogtCF6nuoNEJ3iNiE7xdVNd94qeDxKnwfo+WNtD9ftsXj8bdQfxWJjTEH4iiCCCM4eY6feXVIxh5Z7BwR/ho2GJ+HTB8p2i73qzcI3Y0A/y5tEneaOwEQ0tsUUwemCaZoFwD6pxIK1E4LpRdMkQ82zxNUcRBssz";
            String suffix = "png";
            String path = base64Service.generateImage(reader, name[i], suffix);
            log.info("文件上传路径为：" + path);
            if (path == null) {
                log.info("文件上传失败");

            } else {
                Goods good = new Goods(name[i], kind, path, describe, num, price);
                goodsRepository.save(good);
                log.info("good创建成功");
            }
        }

    }

    @Test
    public void storeTest() {

        int num = 50;
        for (int i = 0; i < 5; i++) {
            Store store = new Store(i, 1, num);
            storeRepository.save(store);
        }


    }

    @Test
    public void time(){
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(s.format(new Date()));
    }
}
