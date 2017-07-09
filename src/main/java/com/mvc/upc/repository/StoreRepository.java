package com.mvc.upc.repository;

import com.mvc.upc.model.Store;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 7/8/2017.
 */
public interface StoreRepository extends CrudRepository<Store, Integer> {
    Store[] findAllByGoodId(int goodId);

}
