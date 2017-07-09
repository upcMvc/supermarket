package com.mvc.upc.repository;

import com.mvc.upc.model.Collection;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 7/8/2017.
 */
public interface CollectionRepository extends CrudRepository<Collection, Integer> {
    Iterable<Collection> findAllByUserIdOrderByCreateTime(int userId);


}
