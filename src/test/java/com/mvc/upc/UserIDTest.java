package com.mvc.upc;

import com.mvc.upc.security.model.*;
import com.mvc.upc.security.service.JwtTokenUtil;
import com.mvc.upc.service.AddAuthority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lylllcc on 2017/7/11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIDTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AddAuthority addAuthority;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void testId(){
        String id = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXYiLCJjcmVhdGVkIjoxNDk5NzQzMzM4NjQ3LCJpZCI6MSwiZXhwIjoxNTAwMzQ4MTM4fQ.2HgpoBKtrRnTgt0eFYTrFHdVVTTeuLafCu9G_OrGtn0ba0Z7SuBYHiaycADp_IVm9BsxOgba6ZT8JkKOdQYEhQ";
        System.out.println(jwtTokenUtil.getUserIdFromToken(id));
    }

    @Test
    public void add(){
        User user = userRepository.findOne(1);
        Authority authority = authorityRepository.findFirstByName(AuthorityName.ROLE_ADMIN);
        if(authority==null){
            authority = new Authority();
            authority.setName(AuthorityName.ROLE_ADMIN);
            authority=authorityRepository.save(authority);
        }
        addAuthority.addAuthority(user,authority);
    }

}
