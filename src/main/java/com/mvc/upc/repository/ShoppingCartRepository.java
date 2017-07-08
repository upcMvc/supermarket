package com.mvc.upc.repository;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 7/8/2017.
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCartRepository, Integer> {
    ShoppingCartRepository findAllByUserId(int userId);
}
