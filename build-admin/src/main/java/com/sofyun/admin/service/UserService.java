package com.sofyun.admin.service;

import com.sofyun.admin.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author gm
 * @since 2019-02-26
 */
public interface UserService extends IService<User> {

    User insert(User user);

    /**
     * 根据手机号查询用户
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 根据编码查询用户
     * @param code
     * @return
     */
    User findByCode(String code);

}
