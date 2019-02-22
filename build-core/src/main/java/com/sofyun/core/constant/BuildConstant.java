package com.sofyun.core.constant;

import java.io.Serializable;

/**
 * @ClassName BuildConstant
 * @Description TODO
 * @Author gm
 * @Date 2019/2/20 16:11
 * @Version 1.0
 **/
public class BuildConstant implements Serializable {

    private static final long serialVersionUID = 3237017178225667285L;

    public final static String BUILD_PATH = "/build-admin/src/main/java";

    public final static String DATA_PATH = "jdbc:mysql://IP:PORT/DATABASE?useUnicode=true&useSSL=false&characterEncoding=utf8";

    public final static String JDBC_DATA_PATH = "jdbc:mysql://IP:PORT/?user=USER&password=PWD&useUnicode=true&useSSL=false&characterEncoding=utf8";



}
