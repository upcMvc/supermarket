package com.mvc.upc.security.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by lylllcc on 2017/7/10.
 */
public interface AuthorityRepository extends CrudRepository<Authority,Integer>{
    Authority findFirstByName(AuthorityName name);
}
