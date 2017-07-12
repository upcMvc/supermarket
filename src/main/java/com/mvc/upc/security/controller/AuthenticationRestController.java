package com.mvc.upc.security.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.security.service.JwtAuthenticationResponse;
import com.mvc.upc.security.service.JwtTokenUtil;
import com.mvc.upc.security.service.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lylllcc on 2017/7/9.
 */
@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Object createAuthenticationToken(String username, String password, Device device) throws AuthenticationException {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password,userDetails.getPassword()) == false){
            return new JsonMes(-1,"用户名或密码错误");
        }
        final String token = jwtTokenUtil.generateToken((JwtUser) userDetails, device);
        Map<Object,Object> map = new HashMap<>();
        map.put("user",userDetails);
        map.put("tocken",token);
        return map;
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}