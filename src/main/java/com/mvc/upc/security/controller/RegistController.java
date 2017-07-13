package com.mvc.upc.security.controller;

import com.mvc.upc.config.AppConfig;
import com.mvc.upc.dto.JsonMes;
import com.mvc.upc.dto.SwaggerParameter;
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
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
            }else {
                user = userRepository.findFirstByPhone(phone);
                if (user!=null){
                    return new JsonMes(-1,"手机号已被注册");
                }
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
        sb.append("<a href=\"" + appConfig.front +"/user/validate?&name=");
        sb.append(username);
        sb.append("&validateCode=");
        Double validateCode = username.length() * MODULUS;
        sb.append(validateCode);
        sb.append("&sendDate=");
        sb.append(new Date());
        sb.append("\">" + appConfig.front + "/validate?&mail=");
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

    @PostMapping("/reset_user")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "userId", value = "用户Id", required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name = "email",value = "重设的邮箱",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "phone",value = "重设的手机号",required = true,dataType = "String")
    })
    public Object resetUser(int userId,String email,String phone){
        User user =userRepository.findOne(userId);
        User check = userRepository.findFirstByEmail(email);
        if (check!=null){
            return new JsonMes(-1,"邮箱已存在");
        }else {
            check = userRepository.findFirstByPhone(phone);
            if (check!=null)
                return new JsonMes(-1,"手机号已被注册");
        }
        user.setEmail(email);
        user.setPhone(phone);
        return userRepository.save(user);
    }

    @GetMapping("forget_password")
    @ApiOperation(value = "忘记密码")
    @ApiImplicitParam(paramType = "query",name = "userId",value = "用户ID",required = true,dataType = "int")
    public Object forgetPassword(int userId){
        User user = userRepository.findOne(userId);
        if (user==null)
            return new JsonMes(-1,"用户不存在");
        String email = user.getEmail();
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        System.out.println("随机密码为："+sb);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(sb);
        System.out.println(password);
        user.setPassword(password);

        String message = "您好，"+user.getUsername()+"; 您的密码已设为"+sb
                +";请尽快前往个人中心重置密码确保安全。";
        MailUtils.send(email,message);

        return userRepository.save(user);
    }
}
