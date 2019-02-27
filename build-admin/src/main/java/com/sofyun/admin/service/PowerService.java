package com.sofyun.admin.service;

import com.sofyun.admin.domain.Power;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofyun.admin.domain.request.power.SaveBO;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author gm
 * @since 2019-02-26
 */
public interface PowerService extends IService<Power> {

    Power insert(SaveBO saveBO);

    Power update(Power power);

    void delete(String id);

}
