package com.mvc.upc.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by lylllcc on 2017/7/8.
 */
public class JwtUser implements UserDetails{

    private int id;
    private String username;
    private String password;

    private String phone;
    private String avatar;

    private final Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private final Date lastPasswordResetDate;


    public JwtUser(String username, String password, String phone, String avatar, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.avatar = avatar;
        this.authorities = authorities;
        this.lastPasswordResetDate = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


}
