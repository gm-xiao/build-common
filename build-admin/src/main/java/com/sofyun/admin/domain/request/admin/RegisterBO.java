package com.sofyun.admin.domain.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RegisterBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/26 17:54
 * @Version 1.0
 **/
@Data
@ApiModel(value="注册", description="注册")
public class RegisterBO implements Serializable {
    private static final long serialVersionUID = 1415636918580867887L;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

}
