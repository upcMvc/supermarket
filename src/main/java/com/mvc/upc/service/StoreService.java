package com.mvc.upc.service;


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
     * @param goodid
     * @param wareHouseId
     * @param num
     */
    public void create(int goodid, int wareHouseId, int num) {
        Store store = new Store(goodid, wareHouseId, num);
        storeRepository.save(store);
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
    public List<Goods> findWH(int whId){

        Iterable<Store> stores = storeRepository.findByWareHouseId(whId);
        Iterator<Store> storeIterator = stores.iterator();
        List<Goods> goods = new ArrayList<Goods>();
        while(storeIterator.hasNext()){
            Store s = storeIterator.next();
            Goods good = goodsRepository.findOne(s.getGoodId());
            goods.add(good);
        }
        return goods;
    }
}