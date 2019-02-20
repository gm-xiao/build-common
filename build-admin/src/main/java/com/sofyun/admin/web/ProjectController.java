package com.sofyun.admin.web;


import com.sofyun.admin.domain.Project;
import com.sofyun.admin.domain.request.admin.InitBO;
import com.sofyun.admin.domain.request.project.SaveBO;
import com.sofyun.admin.service.ProjectService;
import com.sofyun.core.constant.ResponseBo;
import com.sofyun.core.constant.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBo<Project>> json(@PathVariable("id") String id){
        Project project = projectService.getById(id);
        ResponseBo<Project> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(project);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "保存项目")
    @PostMapping("/save")
    public ResponseEntity<ResponseBo<Boolean>> save(@RequestBody SaveBO saveBO){
        projectService.insert(saveBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

}

