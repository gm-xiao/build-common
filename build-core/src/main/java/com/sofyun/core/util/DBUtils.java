package com.sofyun.core.util;

import com.sofyun.core.constant.BuildConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * @ClassName DBUtils
 * @Description TODO
 * @Author gm
 * @Date 2019/2/19 16:07
 * @Version 1.0
 **/
public class DBUtils implements Serializable {

    private static final long serialVersionUID = -1051625062902309834L;

    private final static Logger logger = Logger.getLogger("DBUtils");

    @Async
    public void init(DBModel model){
        Connection conn = null;
        Statement stat = null;
        try {

            // 1.连接数据库
            String jdbcUrl = BuildConstant.JDBC_DATA_PATH
                    .replace("IP", model.getIp())
                    .replace("PORT", model.getPort().toString())
                    .replace("USER", model.getUsername())
                    .replace("PWD", model.getPassword());
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl);
            stat = conn.createStatement();

            // 2.创建数据库
            String sql = "drop database if exists " + model.getEnName();

            //stat.execute(sql);
            logger.info(sql.toString());
            sql = "create database " + model.getEnName() + " CHARACTER SET UTF8";

            //stat.execute(sql);
            logger.info(sql.toString());

            // 3.建表
            for(DBModel.Table table : model.getTables()){

                StringBuffer dropSql = new StringBuffer("DROP TABLE IF EXISTS ");
                dropSql.append("`".concat(table.getEnName()).concat("`"));
                logger.info(dropSql.toString());
                //stat.execute(dropSql.toString());

                StringBuffer tableStr = new StringBuffer("CREATE TABLE");
                tableStr.append(" `".concat(table.getEnName()).concat("` ("));
                for (DBModel.Column column : table.getColumns()){
                    tableStr.append(buildTable(column));
                }
                tableStr.append("PRIMARY KEY (`id`)");
                tableStr.append(")".concat(" ").concat("ENGINE=InnoDB DEFAULT CHARSET=utf8"));

                logger.info(tableStr.toString());

                //stat.execute(tableStr.toString());

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, stat);
        }
    }

    public String buildTable(DBModel.Column column){
        StringBuffer field = new StringBuffer();
        field.append("`".concat(column.getEnName()).concat("` "));
        field.append(column.getType());
        switch (column.getType()){
            case "int" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            case "smallint" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            case "tinyint" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            case "mediumint" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            case "integer" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            case "bigint" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            case "bit" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            case "float" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(",")
                        .concat(String.valueOf(column.getScale())).concat(")"));
                break;
            case "double" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(",")
                        .concat(String.valueOf(column.getScale())).concat(")"));
                break;
            case "decimal" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(",")
                        .concat(String.valueOf(column.getScale())).concat(")"));
                break;
            case "char" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            case "varchar" :
                field.append("(".concat(String.valueOf(column.getLength())).concat(")"));
                break;
            default:
                break;
        }
        if ("0".equals(column.getIsNullable())){
            field.append(" ".concat("NOT NULL"));
        }else {
            field.append(" ".concat("DEFAULT NULL"));
        }
        if (StringUtils.isNotBlank(column.getName())){
            field.append(" ".concat("COMMENT").concat(" '").concat(column.getName()).concat("'").concat(","));
        }
        return field.toString();
    }

    @Async
    public void createDataBase(String url, String name){
        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();

            String sql = "drop database if exists " + name;
            stat.execute(sql);

            sql = "create database " + name + " CHARACTER SET UTF8";

            stat.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, stat);
        }
    }

    @Async
    public void createTable(String url, String database, String tableName){
        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();

            String sql = "use".concat(" ").concat(database);
            stat.execute(sql);

            StringBuffer dropSql = new StringBuffer("DROP TABLE IF EXISTS ");
            dropSql.append("`".concat(tableName).concat("`"));
            System.out.println(dropSql.toString());
            stat.execute(dropSql.toString());

            StringBuffer tableStr = new StringBuffer("CREATE TABLE");
            tableStr.append(" `".concat(tableName).concat("` ("));
            // @TODO 字段构建
            tableStr.append("");
            tableStr.append(")".concat(" ").concat("ENGINE=InnoDB DEFAULT CHARSET=utf8"));
            System.out.println(tableStr.toString());
            stat.execute(tableStr.toString());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, stat);
        }
    }

    private void close(Connection conn, Statement stat){
        try {
            if (null != stat){
                stat.close();
            }
            if (null != conn){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
