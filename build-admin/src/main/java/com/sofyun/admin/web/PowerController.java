package com.sofyun.admin.web;


import com.sofyun.admin.domain.Power;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.power.SaveBO;
import com.sofyun.admin.service.PowerService;
import com.sofyun.core.constant.ResponseBo;
import com.sofyun.core.constant.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author gm
 * @since 2019-02-26
 */
@RestController
@RequestMapping("/power")
@Api(value="权限管理",tags={"权限管理API"})
public class PowerController {

    @Autowired
    private PowerService powerService;

    @ApiOperation(value = "获取权限对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "query")
    @GetMapping("/json")
    public ResponseEntity<ResponseBo<Power>> json(String id){
        Power power = powerService.getById(id);
        ResponseBo<Power> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(power);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "保存权限")
    @PostMapping("/save")
    public ResponseEntity<ResponseBo<Power>> save(@RequestBody SaveBO saveBO){
        Power power = powerService.insert(saveBO);
        ResponseBo<Power> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(power);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "更新权限")
    @PostMapping("/update")
    public ResponseEntity<ResponseBo<Boolean>> update(@RequestBody Power power){
        powerService.update(power);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "删除权限")
    @PostMapping("/delete")
    public ResponseEntity<ResponseBo<Boolean>> delete(@RequestBody DeleteBO deleteBO){
        powerService.delete(deleteBO.getId());
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

}

