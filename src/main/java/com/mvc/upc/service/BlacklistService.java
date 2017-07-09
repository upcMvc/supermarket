package com.mvc.upc.service;

import com.mvc.upc.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanghaojun on 2017/7/9.
 */
@Service
public class BlacklistService {

    @Autowired
    private BlacklistRepository blacklistRepository;



}
