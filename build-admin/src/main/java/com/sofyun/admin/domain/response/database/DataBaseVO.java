package com.sofyun.admin.domain.response.database;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DataBaseVO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/19 15:19
 * @Version 1.0
 **/
@Data
@ApiModel(value="DataBase对象", description="数据库")
public class DataBaseVO implements Serializable {

    private static final long serialVersionUID = -2652899145186889740L;

    private String id;

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

    @ApiModelProperty(value = "数据库类型")
    private String type;

    @ApiModelProperty(value = "项目ID")
    private String project;

}
