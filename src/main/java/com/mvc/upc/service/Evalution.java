package com.mvc.upc.service;

import com.mvc.upc.model.ShopRecord;
import com.mvc.upc.repository.ShopRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jay on 7/9/2017.
 */
@Service
public class Evalution {
    @Autowired
    ShopRecordRepository shopRecordRepository;

    public void evalution(int id, String evalution) {
        ShopRecord shopRecord = shopRecordRepository.findFirstById(id);
        shopRecord.setEvalution(evalution);
        shopRecord.setStatus(2);
    }
}
