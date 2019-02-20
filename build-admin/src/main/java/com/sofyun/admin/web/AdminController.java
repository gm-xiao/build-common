package com.sofyun.admin.web;

import com.sofyun.admin.domain.request.admin.InitBO;
import com.sofyun.core.constant.BuildConstant;
import com.sofyun.core.constant.ResponseBo;
import com.sofyun.core.util.GeneratorBuilder;
import com.sofyun.core.util.GeneratorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AdminController
 * @Description 管理员操作接口
 * @Author gm
 * @Date 2019/2/20 11:24
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
@Api(value="管理员",tags={"管理员操作接口"})
public class AdminController {

    @ApiOperation(value = "初始化项目")
    @PostMapping("/init")
    public ResponseEntity<ResponseBo<Boolean>> init(@RequestBody InitBO initBO){
        String dataUrl = BuildConstant.DATA_PATH
                .replace("IP", initBO.getIp())
                .replace("PORT", initBO.getProt().toString())
                .replace("DATABASE", initBO.getDataBase());
        GeneratorUtils generatorUtils = new GeneratorBuilder()
                .outputDir(initBO.getOutputDir() + BuildConstant.BUILD_PATH)
                .author(initBO.getAuthor())
                .dataUrl(dataUrl)
                .driverName("com.mysql.jdbc.Driver")
                .username(initBO.getUsername())
                .password(initBO.getPassword())
                .build();
        generatorUtils.run();
        ResponseBo<Boolean> responseBo = new ResponseBo<>();
        responseBo.setCode(HttpStatus.OK.value());
        responseBo.setData(true);
        return ResponseEntity.ok(responseBo);
    }


}
