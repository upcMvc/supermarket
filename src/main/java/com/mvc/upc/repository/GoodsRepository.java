package com.mvc.upc.repository;

import com.mvc.upc.model.Goods;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chenzifeng on 2017/7/8.
 */
public interface GoodsRepository extends CrudRepository<Goods,Integer> {
}
