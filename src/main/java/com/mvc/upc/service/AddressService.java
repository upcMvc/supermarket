package com.mvc.upc.service;

import com.mvc.upc.model.Address;
import com.mvc.upc.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanghaojun on 2017/7/9.
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * 创建一个地址
     * @param userId
     * @param location
     * @return
     */
    public Address create(int userId,String location){
        Address address=new Address();
        address.setUserId(userId);
        address.setLocation(location);
        return addressRepository.save(address);
    }

    /**
    根据userId查询地址
    @param userId
     @return address
     **/
    public Iterable<Address> findByUserId(int userId){
        return addressRepository.findByUserId(userId);
    }

    /**
     * 根据addressId查询某用户所有地址
     * @param addressId
     * @return address
     */
    public Address findOne(int addressId){
        return addressRepository.findOne(addressId);
    }

    /**
     * 更新地址
     * @param address
     * @return
     */
    public Address update(Address address){
        return addressRepository.save(address);
    }

    /**
     * 删除一个地址
     * @param addressId
     */
    public void delete(int addressId){
        addressRepository.delete(addressId);
    }



}
