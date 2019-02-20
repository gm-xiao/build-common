package com.sofyun.core.constant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResponseBo
 * @Description TODO
 * @Author gm
 * @Date 2019/2/19 16:07
 * @Version 1.0
 **/
@Data
@ApiModel(value = "响应")
public class ResponseBo<T> implements Serializable{
	
	private static final long serialVersionUID = 7213095123438097838L;

	@ApiModelProperty(value = "响应码", required = true)
	private int code;

	@ApiModelProperty(value = "消息", required = true)
	private String msg;

	@ApiModelProperty(value = "数据", required = true)
	private T data;

}
