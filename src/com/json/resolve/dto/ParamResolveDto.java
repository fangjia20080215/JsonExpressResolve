package com.json.resolve.dto;


import java.io.Serializable;


public class ParamResolveDto implements Serializable {

    /**
     * 表达式名称key
     */
    private String paramKey;

    /**
     * 表达式级别
     */
    private Integer level;


    /**
     * 表达式类型，1：普通类型  2：数组类型
     */
    private Integer type;

    /**
     * 如果是数组类型，index代表数组元素的下标
     * 如何type=1，此字段无值
     */
    private Integer index;


    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
