package com.mvc.upc.security.controller;

import com.mvc.upc.config.AppConfig;
import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.security.model.*;
import com.mvc.upc.security.service.JwtTokenUtil;
import com.mvc.upc.security.service.JwtUser;
import com.mvc.upc.service.Base64Service;
import com.mvc.upc.util.MailUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lylllcc on 2017/7/9.
 */
@RestController
@RequestMapping("/user")
public class RegistController {

    //验证码计算系数
    private static final double MODULUS = 2.56;

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

    @Autowired
    private AppConfig appConfig;

    @ApiOperation(value = "注册接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "imgStr", value = "图片base64", required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "suffix",value = "图片格式",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "password",value = "密码",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "phone",value = "电话",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "email",value = "邮箱",required = true,dataType = "String")
    })
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public Object regist(String imgStr,String suffix,String username,String password,String phone, String email,Device device){
        Base64Service base64Service = new Base64Service();
        String avatar = base64Service.generateImage(imgStr,"avatar",suffix);
        User user = userRepository.findFirstByUsername(username);
        if(user != null){
            return new JsonMes(-1,"用户名已存在");
        }else {
            user = userRepository.findFirstByEmail(email);
            if(user != null){
                return new JsonMes(-1,"邮箱已存在");
            }
        }
        user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setAvatar(avatar);
        user.setEmail(email);
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
        user = userRepository.save(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken((JwtUser) userDetails, device);

        StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时有效，否则重新注册账号，链接只能使用一次，请尽快激活 ！</br>");
        sb.append("<a href=\"" + appConfig.serveraddress +"/user/validate?&name=");
        sb.append(username);
        sb.append("&validateCode=");
        Double validateCode = username.length() * MODULUS;
        sb.append(validateCode);
        sb.append("&sendDate=");
        sb.append(new Date());
        sb.append("\">" + appConfig.serveraddress + "/validate?&mail=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(validateCode);
        sb.append("&sendDate=");
        sb.append(new Date());
        sb.append("</a>");

        MailUtils.send(email, sb.toString());

        Map<Object,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",user);
        return map;
    }


    @ApiOperation(value = "验证接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "name", value = "用户名", required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "validateCode",value = "验证码",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "sendDate",value = "发送日期",required = true,dataType = "Date")
    })
    @GetMapping("/validate")
    public Object validate(String name, Double validateCode, Date sendDate) throws UnsupportedEncodingException {
        Date currentDate = new Date();
        long timeSpan = currentDate.getTime() - sendDate.getTime();
        if (name.length() * MODULUS != validateCode) {
            return new JsonMes(-1,"非法的验证邮件");
        } else if ((timeSpan / 1000 / 60 / 60) > 48) {
            return new JsonMes(-1,"验证邮件失效，请重新验证");
        } else {
            User user = userRepository.findFirstByEmail(name);
            System.out.println(user.getUsername());
            if (user != null) {
                user.setEnabled(true);
                userRepository.save(user);
                return new JsonMes(1,"验证成功");
            } else {
                return new JsonMes(-1,"验证失败  没有此用户 请重新注册");
            }
        }

    }


    @ApiOperation(value = "发送重置密码", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "mail", value = "邮件", required = true,dataType = "String")
    })
    @GetMapping("/reset_mail")
    public Object reset(String mail) {
        System.out.println("mail " + mail);
        if (userRepository.findFirstByEmail(mail) != null) {
            try {
                ///邮件的内容
                StringBuffer sb = new StringBuffer("点击下面链接进行密码重置，48小时内生效，链接只能使用一次，请尽快重置！</br>");
                sb.append("<a href=\"" + appConfig.serveraddress +"/reset_pager?&mail=");
                sb.append(mail);
                sb.append("&validateCode=");
                Double validateCode = mail.length() * MODULUS;
                sb.append(validateCode);
                sb.append("&sendDate=");
                sb.append(new Date());
                sb.append("\">" + appConfig.serveraddress + "/reset_pager?&mail=");
                sb.append(mail);
                sb.append("&validateCode=");
                sb.append(validateCode);
                sb.append("&sendDate=");
                sb.append(new Date());
                sb.append("</a>");

                //发送邮件
                MailUtils.send(mail, sb.toString());
                return new JsonMes(1,"重置密码邮件发送成功");
            } catch (Exception ex) {
                ex.printStackTrace();
                return new JsonMes(-1,"未知错误");
            }
        } else {
            return new JsonMes(-1,"未知错误");
        }

    }
}
