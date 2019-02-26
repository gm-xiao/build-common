package com.sofyun.admin.service;

import com.sofyun.admin.domain.User;
import com.sofyun.auth.TokenUser;
import com.sofyun.auth.TokenUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

@Service
public class AuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByCode(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            Date date = null;
            if (null != user.getLastLoginTime()){
                ZoneId zone = ZoneId.systemDefault();
                Instant instant = user.getLastLoginTime().atZone(zone).toInstant();
                date = Date.from(instant);
            }
            return TokenUserFactory.create(user.getId(), user.getCode(), user.getPwd(), null, date);
        }
    }


}
