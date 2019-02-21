package com.sofyun.admin.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofyun.admin.domain.DataColumn;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.datacolumn.SaveBO;
import com.sofyun.admin.domain.request.datacolumn.UpdateBO;
import com.sofyun.admin.service.DataColumnService;
import com.sofyun.core.constant.ResponseBo;
import com.sofyun.core.constant.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 数据表字段 前端控制器
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@RestController
@RequestMapping("/data-column")
@Api(value="数据字段管理",tags={"数据字段管理接口"})
public class DataColumnController {

    @Autowired
    private DataColumnService dataColumnService;

    @ApiOperation(value = "获取数据字段对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "query")
    @GetMapping("/json")
    public ResponseEntity<ResponseBo<DataColumn>> json(String id){
        DataColumn dataColumn = dataColumnService.getById(id);
        ResponseBo<DataColumn> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(dataColumn);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "获取数据表字段")
    @ApiImplicitParam(name = "table", value = "数据表ID", required = true, dataType = "String", paramType = "query")
    @GetMapping("/listColumn")
    public ResponseEntity<ResponseBo<List<DataColumn>>> listColumn(String table){
        QueryWrapper<DataColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("data_table", table);
        List<DataColumn> dataColumns = dataColumnService.list(queryWrapper);
        ResponseBo<List<DataColumn>> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(dataColumns);
        return ResponseEntity.ok(responseBo);
    }


    @ApiOperation(value = "保存数据库表字段")
    @PostMapping("/save")
    public ResponseEntity<ResponseBo<Boolean>> save(@RequestBody SaveBO saveBO){
        dataColumnService.insert(saveBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "更新数据库表字段")
    @PostMapping("/update")
    public ResponseEntity<ResponseBo<Boolean>> update(@RequestBody UpdateBO updateBO){
        dataColumnService.update(updateBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "删除数据库表字段")
    @PostMapping("/delete")
    public ResponseEntity<ResponseBo<Boolean>> delete(@RequestBody DeleteBO deleteBO){
        dataColumnService.delete(deleteBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

}

