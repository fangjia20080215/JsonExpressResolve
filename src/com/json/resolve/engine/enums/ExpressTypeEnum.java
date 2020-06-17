package com.json.resolve.engine.enums;


public enum  ExpressTypeEnum {

    /**
     * 普通类型
     */
    COMMON_TYPE(1, "COMMON_TYPE"),

    /**
     * 数组类型
     */
    ARRAY_TYPE(2, "ARRAY_TYPE");

    private Integer code;
    private String name;

    ExpressTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
