package com.mvc.upc.repository;

import com.mvc.upc.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jay on 7/8/2017.
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findAllByUserIdOrderByCreateTime(int userId);

    ShoppingCart findAllByUserIdAndGoodIdOrderByCreateTime(int userId,int goodId);

    ShoppingCart findFirstById(int id);
}
