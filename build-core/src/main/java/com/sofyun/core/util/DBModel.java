package com.sofyun.core.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName DBModel
 * @Description TODO
 * @Author gm
 * @Date 2019/2/21 11:01
 * @Version 1.0
 **/
@Data
public class DBModel implements Serializable {

    private static final long serialVersionUID = 8663114408921038220L;

    private String name;

    private String enName;

    private String ip;

    private Integer port;

    private String username;

    private String password;

    private List<Table> tables;

    @Data
    public static class Table{

        private String name;

        private String enName;

        private List<Column> columns;

    }

    @Data
    public static class Column{

        private String name;

        private String enName;

        private Integer precision;

        private Integer scale;

        private String unique;

        private String nullable;

        private String type;

    }


}
