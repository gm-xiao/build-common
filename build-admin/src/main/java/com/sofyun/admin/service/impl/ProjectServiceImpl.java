package com.sofyun.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sofyun.admin.constant.BuildState;
import com.sofyun.admin.domain.DataBase;
import com.sofyun.admin.domain.Project;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.project.InitBO;
import com.sofyun.admin.domain.request.project.SaveBO;
import com.sofyun.admin.domain.request.project.UpdateBO;
import com.sofyun.admin.mapper.DataBaseMapper;
import com.sofyun.admin.mapper.ProjectMapper;
import com.sofyun.admin.service.DataBaseService;
import com.sofyun.admin.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofyun.core.constant.BuildConstant;
import com.sofyun.core.constant.Status;
import com.sofyun.core.exception.BaseException;
import com.sofyun.core.util.GeneratorBuilder;
import com.sofyun.core.util.GeneratorUtils;
import com.sofyun.core.util.GitUtils;
import com.sofyun.core.util.IdUtils;
import org.eclipse.jgit.lib.Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private GitUtils gitUtils;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private DataBaseService dataBaseService;

    @Value("${git.local.repo.path}")
    private String localPath;

    @Value("${git.remote.repo.url}")
    private String remoteUrl;

    @Value("${git.remote.repo.branch}")
    private String branch;

    @Value("${git.username}")
    private String user;

    @Value("${git.password}")
    private String pwd;

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

    @Override
    public void updateBuildState(String id, String buildState){
        Project project = new Project();
        project.setBuildState(buildState);
        UpdateWrapper<Project> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        this.update(project, wrapper);
    }

    @Override
    public void init(InitBO initBO) throws IOException {

        // 1.获取基本信息
        Project project = projectMapper.selectById(initBO.getId());
        if (null == project){
            throw new BaseException(Status.BAD_REQUEST.getCode(), "参数错误");
        }
        if (!BuildState.INIT_DATE_BASE.value().equals(project.getBuildState())){
            throw new BaseException(Status.BAD_REQUEST.getCode(), "数据库未初始化");
        }
        QueryWrapper<DataBase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project", project.getId());
        DataBase dataBase = dataBaseService.getOne(queryWrapper);
        if (null == dataBase){
            throw new BaseException(Status.BAD_REQUEST.getCode(), "数据库信息不完善");
        }

        // 2.拉取远程仓库代码
        gitUtils.setupRepository(user, pwd, remoteUrl, branch, localPath + project.getEnName());

        // 3.通用代码生成
        String dataUrl = BuildConstant.DATA_PATH
                .replace("IP", dataBase.getIp())
                .replace("PORT", dataBase.getPort().toString())
                .replace("DATABASE", dataBase.getEnName());
        GeneratorUtils generatorUtils = new GeneratorBuilder()
                .outputDir(localPath + project.getEnName() + BuildConstant.BUILD_PATH)
                .author(project.getOwnerName())
                .dataUrl(dataUrl)
                .driverName("com.mysql.jdbc.Driver")
                .username(dataBase.getUsername())
                .password(dataBase.getPassword())
                .build();
        generatorUtils.run();

    }
}
