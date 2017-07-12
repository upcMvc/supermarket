package com.mvc.upc.service;

import com.mvc.upc.model.Goods;
import com.mvc.upc.model.History;
import com.mvc.upc.repository.GoodsRepository;
import com.mvc.upc.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by wanghaojun on 2017/7/12.
 */
@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    GoodsRepository goodsRepository;

    /**
     * 添加历史记录
     * @param goodId
     * @param userId
     * @return
     */
    public History create(int goodId,int userId){
        Goods goods=goodsRepository.findOne(goodId);
        Long time=new Date().getTime();
        History history=new History();
        history.setGoodName(goods.getName());
        history.setImgPath(goods.getImgPath());
        history.setPrice(goods.getPrice());
        history.setGoodId(goodId);
        history.setUserId(userId);
        history.setTime(time);
        return historyRepository.save(history);
    }

    /**
     * 删除记录
     * @param id
     */
    public void delete(int id){
        historyRepository.delete(id);
    }

    /**
     * 查询用户浏览记录
     * @param userId
     * @return  history对象数组
     */
    public Object findAllByUserId(int userId){
        return historyRepository.findByUserIdOrderByTimeDesc(userId);
    }


}
