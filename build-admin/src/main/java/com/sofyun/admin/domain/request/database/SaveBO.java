package com.sofyun.admin.domain.request.database;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SaveBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/19 17:04
 * @Version 1.0
 **/
@Data
@ApiModel(value="保存数据库信息", description="保存数据库信息")
public class SaveBO implements Serializable {
    private static final long serialVersionUID = -7132948617603506741L;

    @ApiModelProperty(value = "数据库名称")
    private String name;

    @ApiModelProperty(value = "英文简称")
    private String enName;

    @ApiModelProperty(value = "数据库IP地址")
    private String ip;

    @ApiModelProperty(value = "数据库端口")
    private Integer port;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "数据库类型(1.mysql)")
    private String type;

    @ApiModelProperty(value = "项目ID")
    private String project;
}
