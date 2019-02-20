package com.sofyun.core.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName DBUtils
 * @Description TODO
 * @Author gm
 * @Date 2019/2/19 16:07
 * @Version 1.0
 **/
public class DBUtils {

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
