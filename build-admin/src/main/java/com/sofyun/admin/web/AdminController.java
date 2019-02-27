package com.sofyun.admin.web;

import com.sofyun.admin.domain.request.admin.InitBO;
import com.sofyun.admin.domain.request.admin.LoginBO;
import com.sofyun.admin.domain.request.admin.RegisterBO;
import com.sofyun.admin.service.AdminService;
import com.sofyun.core.constant.BuildConstant;
import com.sofyun.core.constant.ResponseBo;
import com.sofyun.core.constant.Status;
import com.sofyun.core.exception.BaseException;
import com.sofyun.core.util.GeneratorBuilder;
import com.sofyun.core.util.GeneratorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private AdminService adminService;

    @Value("${auth.header}")
    private String tokenHeader;

//    @ApiOperation(value = "初始化项目")
//    @PostMapping("/init")
//    public ResponseEntity<ResponseBo<Boolean>> init(@RequestBody InitBO initBO){
//        String dataUrl = BuildConstant.DATA_PATH
//                .replace("IP", initBO.getIp())
//                .replace("PORT", initBO.getProt().toString())
//                .replace("DATABASE", initBO.getDataBase());
//        GeneratorUtils generatorUtils = new GeneratorBuilder()
//                .outputDir(initBO.getOutputDir() + BuildConstant.BUILD_PATH)
//                .author(initBO.getAuthor())
//                .dataUrl(dataUrl)
//                .driverName("com.mysql.jdbc.Driver")
//                .username(initBO.getUsername())
//                .password(initBO.getPassword())
//                .build();
//        generatorUtils.run();
//        ResponseBo<Boolean> responseBo = new ResponseBo<>();
//        responseBo.setCode(HttpStatus.OK.value());
//        responseBo.setData(true);
//        return ResponseEntity.ok(responseBo);
//    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseEntity<ResponseBo<Object>> login(@RequestBody LoginBO loginBO){
        ResponseBo<Object> responseBo = new ResponseBo<>();
        try {
            final String token = adminService.login(loginBO.getUsername(),loginBO.getPassword());
            responseBo.setCode(Status.SUCCESS.getCode());
            responseBo.setData(token);
        } catch (BaseException e) {
            e.printStackTrace();
            responseBo.setCode(e.getCode());
            responseBo.setMsg(e.getMessage());
            responseBo.setData(false);
        }  catch (BadCredentialsException e) {
            e.printStackTrace();
            responseBo.setCode(Status.ERROR.getCode());
            responseBo.setMsg("用户名或密码错误");
            responseBo.setData(false);
        } catch (Exception e) {
            e.printStackTrace();
            responseBo.setCode(Status.ERROR.getCode());
            responseBo.setMsg("系统错误");
            responseBo.setData(false);
        }

        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ResponseEntity<ResponseBo<Object>> register(@RequestBody RegisterBO registerBO){
        ResponseBo<Object> responseBo = new ResponseBo<>();
        try {
            adminService.register(registerBO);
            responseBo.setCode(Status.SUCCESS.getCode());
            responseBo.setMsg("注册成功");
        } catch (BaseException e) {
            e.printStackTrace();
            responseBo.setCode(e.getCode());
            responseBo.setMsg(e.getMessage());
            responseBo.setData(false);
        } catch (Exception e) {
            e.printStackTrace();
            responseBo.setCode(Status.ERROR.getCode());
            responseBo.setMsg("系统错误");
            responseBo.setData(false);
        }
        return ResponseEntity.ok(responseBo);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        ResponseBo<Object> responseBo = new ResponseBo<>();
        String token = request.getHeader(tokenHeader);
        String refreshedToken = adminService.refresh(token);
        if(refreshedToken == null) {
            responseBo.setCode(Status.ERROR.getCode());
            responseBo.setData(null);
            return ResponseEntity.badRequest().body(responseBo);
        } else {
            responseBo.setCode(Status.SUCCESS.getCode());
            responseBo.setData(token);
            return ResponseEntity.ok(responseBo);
        }
    }


}
