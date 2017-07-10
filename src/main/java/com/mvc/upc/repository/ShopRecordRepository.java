package com.mvc.upc.repository;

import com.mvc.upc.model.ShopRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 7/8/2017.
 */
public interface ShopRecordRepository extends CrudRepository<ShopRecord, Integer> {
    Iterable<ShopRecord> findAllByUserIdAndStatusIsLessThanOrderByCreateTime(int userId, int status);

    ShopRecord findFirstById(int id);
}