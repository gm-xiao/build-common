package com.sofyun.admin.domain.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName InitBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/20 16:00
 * @Version 1.0
 **/
@Data
@ApiModel(value="初始化", description="初始化对象")
public class InitBO implements Serializable {

    private static final long serialVersionUID = 8379302592302798571L;

    @ApiModelProperty(value = "项目路径", required = true)
    private String outputDir;

    @ApiModelProperty(value = "作者", required = true)
    private String author;

    @ApiModelProperty(value = "数据库IP", required = true)
    private String ip;

    @ApiModelProperty(value = "数据库端口", required = true)
    private Integer prot;

    @ApiModelProperty(value = "数据库", required = true)
    private String dataBase;

    @ApiModelProperty(value = "数据库用户", required = true)
    private String username;

    @ApiModelProperty(value = "数据库密码", required = true)
    private String password;

}
