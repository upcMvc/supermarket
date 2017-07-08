package com.mvc.upc.repository;

import com.mvc.upc.model.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wanghaojun on 2017/7/8.
 */
public interface AddressRepository extends CrudRepository<Address,Integer> {
}
