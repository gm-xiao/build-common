package com.sofyun.admin.service.impl;

import com.sofyun.admin.domain.User;
import com.sofyun.admin.domain.request.admin.RegisterBO;
import com.sofyun.admin.service.AdminService;
import com.sofyun.admin.service.UserService;
import com.sofyun.auth.TokenUser;
import com.sofyun.auth.TokenUtil;
import com.sofyun.core.constant.Status;
import com.sofyun.core.exception.BaseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author gm
 * @Date 2019/2/26 17:51
 * @Version 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${auth.tokenHead}")
    private String tokenHead;

    @Override
    public User register(RegisterBO registerBO) {
        User user = new User();
        user.setCode(registerBO.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = registerBO.getPassword();
        user.setPwd(encoder.encode(rawPassword));
        user.setCreateTime(LocalDateTime.now());
        user.setStatus("1");
        user.setExamine("0");
        return userService.insert(user);
    }

    @Override
    public String login(String username, String password) {
        User user = userService.findByCode(username);
        if (null == user){
            throw new BaseException(Status.ERROR.getCode(), "用户不存在");
        }else if ("1".equals(user.getIsDelete()) || "1".equals(user.getStatus()) ){
            throw new BaseException(Status.ERROR.getCode(), "用户已冻结");
        }

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = tokenUtil.generateToken(userDetails);

        user.setLastLoginTime(LocalDateTime.now());

        userService.saveOrUpdate(user);

        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = tokenUtil.getUsernameFromToken(token);
        TokenUser user = (TokenUser) userDetailsService.loadUserByUsername(username);
        if (tokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return tokenUtil.refreshToken(token);
        }
        return null;
    }
}
