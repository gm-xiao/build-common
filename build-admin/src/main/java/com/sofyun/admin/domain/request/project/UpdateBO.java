package com.sofyun.admin.domain.request.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UpdateBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/20 16:58
 * @Version 1.0
 **/
@Data
@ApiModel(value="保存项目信息", description="保存项目信息")
public class UpdateBO implements Serializable {

    private static final long serialVersionUID = -2563237834573654635L;

    @ApiModelProperty(value = "ID", required = true)
    private String id;

    @ApiModelProperty(value = "项目名称", required = true)
    private String name;

    @ApiModelProperty(value = "英文简称", required = true)
    private String enName;

    @ApiModelProperty(value = "所属人", required = true)
    private String ownerName;

    @ApiModelProperty(value = "项目说明", required = true)
    private String remark;

    @ApiModelProperty(value = "模板ID", required = true)
    private String template;

}
