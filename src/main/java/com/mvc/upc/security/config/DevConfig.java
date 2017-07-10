package com.mvc.upc.security.config;

import com.mvc.upc.security.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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

        Authority authority = authorityRepository.findFirstByName(AuthorityName.ROLE_USER);
        if (authority == null){
            authority = new Authority();
            authority.setName(AuthorityName.ROLE_USER);
            authority = authorityRepository.save(authority);
        }
        User user = userRepository.findFirstByUsername("dev");
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

    @PostConstruct
    public void addWareHouseAuthority(){

        Authority authority = authorityRepository.findFirstByName(AuthorityName.ROLE_WAREHOUSEADMIN);
        if (authority==null){
            authority = new Authority();
            authority.setName(AuthorityName.ROLE_WAREHOUSEADMIN);
            authority = authorityRepository.save(authority);
        }
        User user = userRepository.findFirstByUsername("warehouser");
        if(user ==null){
            user = new User();
            user.setUsername("warehouser");
            user.setPassword(new BCryptPasswordEncoder().encode("warehouser"));
            user.setPhone("120");
            user = userRepository.save(user);
            List<Authority> authorities = user.getAuthorities();
            authorities.add(authority);
            user.setAuthorities(authorities);
            userRepository.save(user);
        }
    }
}
