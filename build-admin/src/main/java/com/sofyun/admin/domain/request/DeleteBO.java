package com.sofyun.admin.domain.request;

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
@ApiModel(value="删除信息", description="删除信息")
public class DeleteBO implements Serializable {

    private static final long serialVersionUID = -2563237834573654635L;

    @ApiModelProperty(value = "ID", required = true)
    private String id;

}
