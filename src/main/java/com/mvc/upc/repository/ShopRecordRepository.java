package com.mvc.upc.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 7/8/2017.
 */
public interface ShopRecordRepository extends CrudRepository<ShopRecordRepository, Integer> {
    ShoppingCartRepository findAllByUserId(int userId);

}
