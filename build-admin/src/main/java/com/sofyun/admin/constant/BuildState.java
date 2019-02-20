package com.sofyun.admin.constant;

import com.sofyun.core.constant.Status;

public enum BuildState {

    /**
     * 初始化
     */
    INIT("0", "初始化"),

    /**
     * 创建数据库
     */
    CREATE_DATE_BASE("1", "创建数据库"),

    /**
     * 创建表结构
     */
    CREATE_DATE_TABLE("2", "创建表结构"),

    /**
     * 构建完成
     */
    OUT("3", "构建完成"),;

    private String value;

    private String message;

    BuildState(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String value() {
        return this.value;
    }

    public static BuildState formValue(String value) {
        for (BuildState status : values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return INIT;
    }

}
