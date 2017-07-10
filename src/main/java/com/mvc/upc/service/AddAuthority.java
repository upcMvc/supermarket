package com.mvc.upc.service;

import com.mvc.upc.security.model.Authority;
import com.mvc.upc.security.model.AuthorityRepository;
import com.mvc.upc.security.model.User;
import com.mvc.upc.security.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lylllcc on 2017/7/10.
 */
@Service
public class AddAuthority {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    public void addAuthority(User user, Authority authority){
        User user1 = userRepository.findFirstByUsername(user.getUsername());
        if(user1 == null){
            user1 = userRepository.save(user);
        }
        List<Authority> list = user1.getAuthorities();
        list.add(authority);
        user1.setAuthorities(list);
        userRepository.save(user1);
    }

}
