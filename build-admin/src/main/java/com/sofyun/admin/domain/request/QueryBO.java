package com.sofyun.admin.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName QueryBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/21 10:48
 * @Version 1.0
 **/
@Data
public class QueryBO implements Serializable {
    private static final long serialVersionUID = -7463579725241834432L;

    @ApiModelProperty(value = "ID", required = false)
    private String id;

}
