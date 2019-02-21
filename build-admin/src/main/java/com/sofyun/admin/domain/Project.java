package com.sofyun.admin.domain;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author gm
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Project对象", description="项目")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "项目名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "英文简称")
    @TableField("en_name")
    private String enName;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "所属人")
    @TableField("owner_name")
    private String ownerName;

    @ApiModelProperty(value = "项目说明")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "状态(0.禁用 1.启用）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "构建状态(0.初始化 1.创建数据库 2.创建表结构 3.构建完成）")
    @TableField("build_state")
    private String buildState;

    @ApiModelProperty(value = "模板ID")
    @TableField("template")
    private String template;

    @ApiModelProperty(value = "模板名称")
    @TableField("template_name")
    private String templateName;


}
