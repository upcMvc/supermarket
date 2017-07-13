package com.mvc.upc.service;


import com.mvc.upc.dto.StoreDto;
import com.mvc.upc.model.Goods;
import com.mvc.upc.model.Store;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.repository.StoreRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chenzifeng on 2017/7/9.
 */
@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    GoodsRepository goodsRepository;

    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * @param goodName
     * @param wareHouseId
     * @param num
     */
    public Object create(String goodName, int wareHouseId, int num) {
        Goods goods = goodsRepository.findFirstByName(goodName);
        System.out.println("goodName:"+goodName);
        System.out.println(goods.getName());
        int goodid = goods.getId();
        System.out.println(goodid);
        Store check = storeRepository.findFirstByGoodIdAndWareHouseId(goodid,wareHouseId);
        if (check==null)
            check = new Store(goodid,wareHouseId,num);
        else{
            check.setGoodNum(num+check.getGoodNum());
        }
        return storeRepository.save(check);
    }

    /**
     * @param storeId
     * @param num
     * @return true:成功
     * false：未找到记录
     */
    public boolean update(int storeId, int num) {
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            log.info("没有找到该记录");
            return false;
        }
        store.setGoodNum(num);
        storeRepository.save(store);
        return true;
    }

    /**
     * @param storeId
     * @return true:成功
     * false：未找到记录
     */
    public boolean delete(int storeId) {

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            log.info("没有找到该记录");
            return false;
        }
        storeRepository.delete(store);
        return true;
    }
    public List<StoreDto> findWH(int whId){

        Iterable<Store> stores = storeRepository.findByWareHouseId(whId);
        Iterator<Store> storeIterator = stores.iterator();
        List<StoreDto> lst = new ArrayList<>();
        while(storeIterator.hasNext()){
            Store s = storeIterator.next();
            Goods good = goodsRepository.findOne(s.getGoodId());
            StoreDto storeDto = new StoreDto(good.getName(),good.getImgPath(),s.getGoodNum(),good.getPrice());
            lst.add(storeDto);
        }
        return lst;
    }
}