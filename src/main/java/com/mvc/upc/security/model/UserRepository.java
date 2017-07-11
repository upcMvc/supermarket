package com.mvc.upc.security.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by lylllcc on 2017/7/9.
 */
public interface UserRepository extends CrudRepository<User,Integer>{
    User findFirstByUsername(String username);
    User findFirstByEmail(String email);
}
