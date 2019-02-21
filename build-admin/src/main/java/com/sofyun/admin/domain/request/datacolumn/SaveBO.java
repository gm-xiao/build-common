package com.sofyun.admin.domain.request.datacolumn;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SaveBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/20 19:47
 * @Version 1.0
 **/
@Data
@ApiModel(value="保存数据字段信息", description="保存数据字段信息")
public class SaveBO implements Serializable {

    private static final long serialVersionUID = 3251801343810832696L;

    @ApiModelProperty(value = "字段名")
    private String name;

    @ApiModelProperty(value = "字段名(英文)")
    private String enName;

    @ApiModelProperty(value = "数据表")
    private String dataTable;

    @ApiModelProperty(value = "长度")
    private Integer length;

    @ApiModelProperty(value = "小数点")
    private Integer scale;

    @ApiModelProperty(value = "是否唯一(0.否 1.是)")
    private String isUnique;

    @ApiModelProperty(value = "是否为空(0.否 1.是)")
    private String isNullable;

    @ApiModelProperty(value = "字段类型")
    private String type;

}