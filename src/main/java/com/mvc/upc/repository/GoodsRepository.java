package com.mvc.upc.repository;

import com.mvc.upc.model.Goods;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;

/**
 * Created by chenzifeng on 2017/7/8.
 */
public interface GoodsRepository extends CrudRepository<Goods,Integer> {

    Iterator<Goods> findByKinds(String kinds);
    Iterable<Goods> findByName(String name);
}
