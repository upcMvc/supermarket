package com.mvc.upc.repository;

import com.mvc.upc.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 7/8/2017.
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

    Iterable<ShoppingCart> findAllByUserIdOrderByCreateTime(int userId);

    ShoppingCart findFirstById(int id);
}
