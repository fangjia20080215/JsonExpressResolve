package com.json.resolve.engine.enums;

/**
 * @ Author zhangpw@missfresh.cn
 * @ Date   2020-05-28 21:01
 * @ Description：场景类型枚举
 */
public enum SceneTypeEnum {
    /**
     *单场景类型
     */
    SINGLE_SCENE(1,"单场景"),

    /**
     * 复合场景类型
     */
    MULTI_SCENE(2,"复合场景");

    private Integer code;
    private String name;

    SceneTypeEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }

    public Integer getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
