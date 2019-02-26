package com.sofyun.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofyun.admin.domain.User;
import com.sofyun.admin.mapper.UserMapper;
import com.sofyun.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofyun.core.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdUtils idUtils;

    @Override
    public User insert(User user) {
        user.setId(idUtils.create());
        this.save(user);
        return user;
    }

    @Override
    public User findByMobile(String mobile) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User findByCode(String code) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        return userMapper.selectOne(queryWrapper);
    }
}
