package com.sofyun.admin.web;


import com.sofyun.admin.domain.Project;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.project.InitBO;
import com.sofyun.admin.domain.request.project.SaveBO;
import com.sofyun.admin.domain.request.project.UpdateBO;
import com.sofyun.admin.service.ProjectService;
import com.sofyun.core.constant.ResponseBo;
import com.sofyun.core.constant.Status;
import com.sofyun.core.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * <p>
 * 项目 前端控制器
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Api(value="项目管理",tags={"项目管理接口"})
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation(value = "获取项目对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "query")
    @GetMapping("/json")
    public ResponseEntity<ResponseBo<Project>> json(String id){
        Project project = projectService.getById(id);
        ResponseBo<Project> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(project);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "保存项目")
    @PostMapping("/save")
    public ResponseEntity<ResponseBo<Project>> save(@RequestBody SaveBO saveBO){
        Project project = projectService.insert(saveBO);
        ResponseBo<Project> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(project);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "更新项目")
    @PostMapping("/update")
    public ResponseEntity<ResponseBo<Boolean>> update(@RequestBody UpdateBO updateBO){
        projectService.update(updateBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "删除项目")
    @PostMapping("/delete")
    public ResponseEntity<ResponseBo<Boolean>> delete(@RequestBody DeleteBO deleteBO){
        projectService.delete(deleteBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "初始化项目")
    @PostMapping("/init")
    public ResponseEntity<ResponseBo<Boolean>> init(@RequestBody InitBO initBO){
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        try {
            projectService.init(initBO);
            responseBo.setCode(Status.SUCCESS.getCode());
            responseBo.setMsg(Status.SUCCESS.getMessage());
            responseBo.setData(true);
        } catch (IOException e) {
            e.printStackTrace();
            responseBo.setCode(Status.ERROR.getCode());
            responseBo.setMsg(Status.ERROR.getMessage());
            responseBo.setData(false);
        } catch (BaseException e){
            e.printStackTrace();
            responseBo.setCode(e.getCode());
            responseBo.setMsg(e.getMessage());
            responseBo.setData(false);
        }
        return ResponseEntity.ok(responseBo);
    }


}

