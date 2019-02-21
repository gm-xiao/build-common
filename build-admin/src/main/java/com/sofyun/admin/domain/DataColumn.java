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
 * 数据表字段
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DataColumn对象", description="数据表字段")
public class DataColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "字段名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "字段名(英文)")
    @TableField("en_name")
    private String enName;

    @ApiModelProperty(value = "数据表")
    @TableField("data_table")
    private String dataTable;

    @ApiModelProperty(value = "长度")
    @TableField("length")
    private Integer length;

    @ApiModelProperty(value = "小数点")
    @TableField("scale")
    private Integer scale;

    @ApiModelProperty(value = "是否唯一(0.否 1.是)")
    @TableField("is_unique")
    private String isUnique;

    @ApiModelProperty(value = "是否为空（0.否 1.是）")
    @TableField("is_nullable")
    private String isNullable;

    @ApiModelProperty(value = "字段类型")
    @TableField("type")
    private String type;


}
