package com.mvc.upc.repository;

import com.mvc.upc.model.History;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wanghaojun on 2017/7/12.
 */
public interface HistoryRepository extends CrudRepository<History,Integer> {

    Iterable<History> findByUserIdOrderByTimeDesc(int userId);
}
