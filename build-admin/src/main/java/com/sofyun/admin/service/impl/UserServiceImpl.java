package com.sofyun.admin.service.impl;

import com.sofyun.admin.domain.User;
import com.sofyun.admin.mapper.UserMapper;
import com.sofyun.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
