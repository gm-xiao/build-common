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
 * 数据表
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DataTable对象", description="数据表")
public class DataTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "表名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "英文表名（简称）")
    @TableField("en_name")
    private String enName;

    @ApiModelProperty(value = "数据库")
    @TableField("data_base")
    private String dataBase;


}
