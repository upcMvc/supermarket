package com.mvc.upc.security.config;

import com.mvc.upc.security.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by lylllcc on 2017/7/10.
 */
@Component
public class DevConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @PostConstruct
    public void adddevUserAndAuthority(){

        Authority authority = authorityRepository.findFirstByName(AuthorityName.ROLE_ADMIN);
        if (authority == null){
            authority = new Authority();
            authority.setName(AuthorityName.ROLE_ADMIN);
            authority = authorityRepository.save(authority);
        }
        User user = userRepository.findByUsername("dev");
        if (user == null){
            user = new User();
            user.setUsername("dev");
            user.setPassword(new BCryptPasswordEncoder().encode("dev"));
            user.setAvatar("http://mpic.tiankong.com/f1c/b02/f1cb0236c5a3a403a46f982729239809/640.jpg@360h");
            user.setPhone("110");
            user.addAuthorty(authority);
            userRepository.save(user);
        }
    }
}