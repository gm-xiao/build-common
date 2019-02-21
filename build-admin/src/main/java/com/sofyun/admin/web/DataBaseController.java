package com.sofyun.admin.web;


import com.sofyun.admin.domain.DataBase;
import com.sofyun.admin.domain.request.database.InitBO;
import com.sofyun.admin.domain.request.database.SaveBO;
import com.sofyun.admin.domain.response.database.DataBaseVO;
import com.sofyun.admin.service.DataBaseService;
import com.sofyun.core.constant.ResponseBo;
import com.sofyun.core.constant.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 数据库 前端控制器
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@RestController
@RequestMapping("/data-base")
@Api(value="数据库管理",tags={"数据库管理接口"})
public class DataBaseController {

    @Autowired
    private DataBaseService dataBaseService;

    @ApiOperation(value = "获取数据库对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "query")
    @GetMapping("/json")
    public ResponseEntity<ResponseBo<DataBaseVO>> json(String id){
        DataBase dataBase = dataBaseService.getById(id);
        DataBaseVO dataBaseVO = new DataBaseVO();
        BeanUtils.copyProperties(dataBase, new DataBaseVO());
        ResponseBo<DataBaseVO> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(dataBaseVO);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "保存数据库对象")
    @PostMapping("/save")
    public ResponseEntity<ResponseBo<DataBaseVO>> save(@RequestBody SaveBO saveBO){
        DataBase dataBase = dataBaseService.insert(saveBO);
        DataBaseVO dataBaseVO = new DataBaseVO();
        BeanUtils.copyProperties(dataBase, new DataBaseVO());
        ResponseBo<DataBaseVO> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(dataBaseVO);
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "初始化数据库")
    @PostMapping("/init")
    public ResponseEntity<ResponseBo<Boolean>> init(@RequestBody InitBO initBO){
        dataBaseService.init(initBO);
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(Status.SUCCESS.getCode());
        responseBo.setMsg(Status.SUCCESS.getMessage());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }




}

