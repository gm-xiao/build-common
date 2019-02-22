package com.sofyun.admin.service;

import com.sofyun.admin.domain.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.project.InitBO;
import com.sofyun.admin.domain.request.project.SaveBO;
import com.sofyun.admin.domain.request.project.UpdateBO;

import java.io.IOException;

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

    Project update(UpdateBO updateBO);

    void delete(DeleteBO deleteBO);

    void updateBuildState(String id, String buildState);

    void init(InitBO initBO) throws IOException;

}
