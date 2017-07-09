package com.mvc.upc.repository;

import com.mvc.upc.model.Blacklist;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chenzifeng on 2017/7/8.
 */
public interface BlacklistRepository extends CrudRepository<Blacklist,Integer> {

    Blacklist findFirstByUserId(int userId);

}
