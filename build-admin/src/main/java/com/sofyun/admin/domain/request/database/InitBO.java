package com.sofyun.admin.domain.request.database;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName InitBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/19 17:04
 * @Version 1.0
 **/
@Data
@ApiModel(value="初始化数据库", description="初始化数据库")
public class InitBO implements Serializable {
    private static final long serialVersionUID = -7132948617603506741L;

    @ApiModelProperty(value = "ID" , required = true)
    private String id;

}
