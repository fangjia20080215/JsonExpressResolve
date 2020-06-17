package com.json.resolve.engine;


import com.json.resolve.dto.ParamResolveDto;

import java.util.List;

public interface IParamResolveEngine {


    /**
     * 解析表达式
     * @param express 表达式
     * @return 表达式对象
     */
    List<ParamResolveDto> paramResolvEngine(String express);


    /**
     * 根据json字符串以及表达式解析对应的字段结果
     * @param jsonStr 待解析的json字符串
     * @param express 表达式
     * @return 字段值，统一返回String
     */
    String executeResolveExpress(String jsonStr, String express);


    /**
     * 根据json字符串以及表达式替换对应的字段结果
     * @param jsonStr 待解析的json字符串
     * @param express 表达式
     * @return 替换之后的json字符串
     */
    String executeReplacePramExpress(String jsonStr, String express, String targetValue);
}