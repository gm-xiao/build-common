package com.sofyun.admin.service;

import com.sofyun.admin.domain.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofyun.admin.domain.request.project.SaveBO;

/**
 * <p>
 * 项目 服务类
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
public interface ProjectService extends IService<Project> {

    Project insert(SaveBO saveBO);

}
