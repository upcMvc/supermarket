package com.mvc.upc.repository;

import com.mvc.upc.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wanghaojun on 2017/7/8.
 */
public interface UserRepository extends CrudRepository<User,Integer> {

}
