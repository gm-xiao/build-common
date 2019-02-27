package com.sofyun.admin.domain.request.power;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SaveBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/27 14:17
 * @Version 1.0
 **/
@Data
@ApiModel(value="权限", description="权限")
public class SaveBO implements Serializable {

    private static final long serialVersionUID = -2924041832035158587L;

    @ApiModelProperty(value = "权限编码", required = true)
    private String code;

    @ApiModelProperty(value = "权限等级", required = true)
    private Integer level;

    @ApiModelProperty(value = "权限名称", required = true)
    private String name;

    @ApiModelProperty(value = "父级权限", required = true)
    private String parentId;

    @ApiModelProperty(value = "权限顺序" , required = true)
    private Integer sort;

    @ApiModelProperty(value = "权限类型(0.菜单 1.动作)", required = true)
    private String type;

    @ApiModelProperty(value = "访问路径", required = true)
    private String url;
}
