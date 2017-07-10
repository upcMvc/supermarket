package com.mvc.upc.security.controller;

import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
import com.mvc.upc.security.model.*;
import com.mvc.upc.security.service.JwtAuthenticationResponse;
import com.mvc.upc.security.service.JwtTokenUtil;
import com.mvc.upc.service.Base64Service;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lylllcc on 2017/7/9.
 */
@RestController
public class RegistController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "创建app", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "imgStr", value = "图片base64", required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "suffix",value = "图片格式",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "password",value = "密码",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "phone",value = "电话",required = true,dataType = "String")
    })

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public ResponseEntity<?> regist(String imgStr,String suffix,String username,String password,String phone, Device device){
        Base64Service base64Service = new Base64Service();
        String avatar = base64Service.generateImage(imgStr,"avatar",suffix);
        User user = userRepository.findFirstByUsername(username);
        if(user != null){
            return ResponseEntity.ok(new JsonMes(-1,"用户名已存在"));
        }
        user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setAvatar(avatar);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        password = bCryptPasswordEncoder.encode(password);
        user.setPassword(password);
        Authority authority = authorityRepository.findFirstByName(AuthorityName.ROLE_USER);
        if(authority == null){
            authority = new Authority();
            authority.setName(AuthorityName.ROLE_USER);
            authority = authorityRepository.save(authority);
        }
        user.addAuthorty(authority);
        userRepository.save(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}
