package com.mvc.upc.service;

import com.mvc.upc.dto.GoodDto;
import com.mvc.upc.model.Address;
import com.mvc.upc.model.Goods;
import com.mvc.upc.model.ShopRecord;
import com.mvc.upc.model.WareHouse;
import com.mvc.upc.repository.AddressRepository;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.repository.ShopRecordRepository;
import com.mvc.upc.repository.WareHouseRepository;
import com.mvc.upc.security.model.User;
import com.mvc.upc.security.model.UserRepository;
import com.mvc.upc.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wanghaojun on 2017/7/9.
 */
@Service
public class WareHouseService {

    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    LocationService locationService;
    @Autowired
    ShopRecordRepository shopRecordRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     * 创建一个仓库
     *
     * @param location
     * @param name
     * @param userId
     * @return
     */
    public WareHouse create(String location, String name, int userId, String city, String coordinate) {
        double longitude = locationService.subLocation(coordinate)[0];
        double latitude = locationService.subLocation(coordinate)[1];
        WareHouse wareHouse = new WareHouse(userId, location, name, city, longitude, latitude);
        return wareHouseRepository.save(wareHouse);
    }

    /**
     * 删除一个仓库
     *
     * @param id
     */
    public void delete(int id) {
        wareHouseRepository.delete(id);
    }


    /**
     * 查找所有仓库
     *
     * @return
     */
    public Iterable<WareHouse> findAll() {
        return wareHouseRepository.findAll();
    }

    /**
     * 根据仓库管理员的userId查找仓库
     *
     * @param userId
     * @return
     */
    public WareHouse findByUserId(int userId) {
        return wareHouseRepository.findFirstByUserId(userId);
    }

    /**
     * 更新仓库信息
     *
     * @param id
     * @param location
     * @param name
     * @param userId
     * @return
     */
    public WareHouse update(int id, String location, String name, int userId) {
        WareHouse wareHouse = wareHouseRepository.findOne(id);
        wareHouse.setUserId(userId);
        wareHouse.setName(name);
        wareHouse.setLocation(location);
        return wareHouseRepository.save(wareHouse);
    }

    /**
     * 更换仓库管理员
     *
     * @param id
     * @param userId
     * @return
     */
    public WareHouse changeUser(int id, int userId) {
        WareHouse wareHouse = wareHouseRepository.findOne(id);
        wareHouse.setUserId(userId);
        return wareHouseRepository.save(wareHouse);
    }

    public String sendEmail(int userId) {
        Iterable<ShopRecord> shopRecords = shopRecordRepository.findAllByUserIdAndStatusOrderByCreateTime(userId, 0);
        Iterator<ShopRecord> isr = shopRecords.iterator();
        String message = null;
        User user = userRepository.findOne(userId);
        System.out.println(user.getUsername());
        message = "顾客的电话为：" + user.getPhone() + ";   顾客所需的商品的为：";
        ShopRecord shopRecord = isr.next();
        //获取仓库管理员的邮箱
        int whid = shopRecord.getWareHouseId();
        WareHouse wareHouse = wareHouseRepository.findOne(whid);
        int whUserId = wareHouse.getUserId();
        User whMaster = userRepository.findOne(whUserId);
        String email = whMaster.getEmail();
        //获取用户提交订单所在的位置
        Address address = addressRepository.findOne(shopRecord.getAddressId());
        String location = address.getLocation();
        //获取商品
        Goods goods = goodsRepository.findOne(shopRecord.getGoodId());
        List<String> goodnames = new ArrayList<>();
        message = message + " " + goods.getName() + " " + shopRecord.getNumber()+",";
//        GoodDto goodDto = new GoodDto(goods.getName(),shopRecord.getNumber());
        goodnames.add(goods.getName());
        while (isr.hasNext()) {
            shopRecord = isr.next();
            int num = 0;
            Goods good = goodsRepository.findOne(shopRecord.getGoodId());
            Iterable<ShopRecord> ls = shopRecordRepository.findAllByUserIdAndGoodIdAndWareHouseIdAndStatusOrderByCreateTime(userId,good.getId(),whid,0);
            Iterator<ShopRecord> lsh = ls.iterator();
            while (lsh.hasNext()){
                ShopRecord sr = lsh.next();
                num = num+ sr.getNumber();
                System.out.println(good.getName()+sr.getNumber());
            }
            if(!goodnames.contains(good.getName())){
                goodnames.add(good.getName());
                message = message + " " + good.getName() + " " + num+",";
            }

        }
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message = message +"</br>"+"用户所在位置: " +location + ";"+"</br>"+"发送时间为："
                + s.format(new Date())+";    请及时送达";
        MailUtils.send(email,message);
        System.out.println(message);
        return message;
    }

}
