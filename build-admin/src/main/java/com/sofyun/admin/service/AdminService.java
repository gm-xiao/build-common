package com.sofyun.admin.service;

import com.sofyun.admin.domain.User;
import com.sofyun.admin.domain.request.admin.RegisterBO;

/**
 * @ClassName AdminService
 * @Description TODO
 * @Author gm
 * @Date 2019/2/26 17:51
 * @Version 1.0
 **/
public interface AdminService {

    User register(RegisterBO registerBO);

    String login(String username, String password);

    String refresh(String token);
}
