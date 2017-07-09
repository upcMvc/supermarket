package com.mvc.upc.service;

import com.mvc.upc.model.Blacklist;
import com.mvc.upc.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by wanghaojun on 2017/7/9.
 */
@Service
public class BlacklistService {

    @Autowired
    private BlacklistRepository blacklistRepository;

    /**
     * 将一个人拉入黑名单
     *
     * @param userId
     * @param reason
     * @return
     */
    public Blacklist create(int userId, String reason) {
        Blacklist blacklist = new Blacklist(userId, reason);
        return blacklistRepository.save(blacklist);
    }

    /**
     * 判断某个user是否在黑名单中,在：返回为true
     *
     * @param userId
     * @return
     */
    public boolean isBlacklist(int userId) {
        Blacklist blacklist=blacklistRepository.findFirstByUserId(userId);
        if (blacklist==null){
            return false;
        }else return true;
    }

    /**
     * 从黑名单中把某个user删除
     *
     * @param userId
     */
    public void delete(int userId) {
        Blacklist blacklist = blacklistRepository.findFirstByUserId(userId);
        blacklistRepository.delete(blacklist.getId());
    }

    /**
     * 查找所有黑名单用户
     *
     * @return
     */
    public Iterable<Blacklist> findAll() {
        return blacklistRepository.findAll();
    }


}
