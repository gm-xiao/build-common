package com.sofyun.admin.domain.request.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName InitBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/22 10:23
 **/
@Data
@ApiModel(value="初始化项目信息", description="初始化项目信息")
public class InitBO implements Serializable {

    private static final long serialVersionUID = -2297900583812029763L;

    @ApiModelProperty(value = "ID", required = true)
    private String id;
}
