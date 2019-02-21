package com.sofyun.admin.domain.request.datatable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UpdateBO
 * @Description TODO
 * @Author gm
 * @Date 2019/2/19 17:04
 * @Version 1.0
 **/
@Data
@ApiModel(value="更新数据表信息", description="更新数据表信息")
public class UpdateBO implements Serializable {
    private static final long serialVersionUID = -7132948617603506741L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "表名")
    private String name;

    @ApiModelProperty(value = "英文表名（简称）")
    private String enName;

    @ApiModelProperty(value = "数据库ID")
    private String dataBase;
}
