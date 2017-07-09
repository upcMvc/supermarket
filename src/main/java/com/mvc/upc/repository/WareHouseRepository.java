package com.mvc.upc.repository;

import com.mvc.upc.model.WareHouse;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chenzifeng on 2017/7/8.
 */
public interface WareHouseRepository extends CrudRepository<WareHouse,Integer> {
    WareHouse findFirstByUserId(int userId);
}
