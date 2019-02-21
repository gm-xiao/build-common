package com.sofyun.admin.service.impl;

import com.sofyun.admin.constant.BuildState;
import com.sofyun.admin.domain.Project;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.project.SaveBO;
import com.sofyun.admin.domain.request.project.UpdateBO;
import com.sofyun.admin.mapper.ProjectMapper;
import com.sofyun.admin.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofyun.core.util.IdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    private IdUtils idUtils;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Project insert(SaveBO saveBO) {
        Project project = new Project();
        BeanUtils.copyProperties(saveBO, project);
        project.setId(idUtils.create());
        project.setBuildState(BuildState.INIT.value());
        project.setCreateTime(LocalDateTime.now());
        project.setUpdateTime(LocalDateTime.now());
        this.save(project);
        return project;
    }

    @Override
    public Project update(UpdateBO updateBO) {
        Project project = new Project();
        BeanUtils.copyProperties(updateBO, project);
        project.setUpdateTime(LocalDateTime.now());
        this.save(project);
        return project;
    }

    @Override
    public void delete(DeleteBO deleteBO) {
        projectMapper.deleteById(deleteBO.getId());
    }

}
