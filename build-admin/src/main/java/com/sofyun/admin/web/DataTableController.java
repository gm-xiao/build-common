package com.sofyun.admin.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofyun.admin.domain.DataTable;
import com.sofyun.admin.domain.request.DeleteBO;
import com.sofyun.admin.domain.request.datatable.SaveBO;
import com.sofyun.admin.domain.request.datatable.UpdateBO;
import com.sofyun.admin.service.DataTableService;
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
 * 数据表 前端控制器
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@RestController
@RequestMapping("/data-table")
@Api(value="数据表管理",tags={"数据表管理接口"})
public class DataTableController {

    @Autowired
    private DataTableService dataTableService;

    @ApiOperation(value = "获取数据表对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "query")
    @GetMapping("/json")
    public ResponseEntity<ResponseBo<DataTable>> json(String id){
        DataTable dataTable = dataTableService.getById(id);
        ResponseBo<DataTable> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(dataTable);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "获取数据库表")
    @ApiImplicitParam(name = "database", value = "数据库ID", required = true, dataType = "String", paramType = "query")
    @GetMapping("/listTable")
    public ResponseEntity<ResponseBo<List<DataTable>>> listTable(String database){
        QueryWrapper<DataTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("data_base", database);
        List<DataTable> dataTables = dataTableService.list(queryWrapper);
        ResponseBo<List<DataTable>> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(dataTables);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "保存数据库表")
    @PostMapping("/save")
    public ResponseEntity<ResponseBo<Boolean>> save(@RequestBody SaveBO saveBO){
        dataTableService.insert(saveBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "更新数据库表")
    @PostMapping("/update")
    public ResponseEntity<ResponseBo<Boolean>> update(@RequestBody UpdateBO updateBO){
        dataTableService.update(updateBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "删除数据库表")
    @PostMapping("/delete")
    public ResponseEntity<ResponseBo<Boolean>> delete(@RequestBody DeleteBO deleteBO){
        dataTableService.delete(deleteBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }


}

