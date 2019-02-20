package com.sofyun.admin.domain;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据库
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DataBase对象", description="数据库")
public class DataBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "数据库名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "英文简称")
    @TableField("en_name")
    private String enName;

    @ApiModelProperty(value = "数据库IP地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "数据库端口")
    @TableField("port")
    private Integer port;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "数据库类型(1.mysql)")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "项目ID")
    @TableField("project")
    private String project;


}
