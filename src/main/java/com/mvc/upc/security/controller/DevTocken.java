package com.mvc.upc.security.controller;

import com.mvc.upc.security.service.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lylllcc on 2017/7/10.
 */
@RestController
public class DevTocken {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @GetMapping("/dev/user")
    public String genToken(){
        final UserDetails userDetails = userDetailsService.loadUserByUsername("dev");

        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
}
