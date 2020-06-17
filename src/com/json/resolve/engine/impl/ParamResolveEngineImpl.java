package com.json.resolve.engine.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.json.resolve.dto.ParamResolveDto;
import com.json.resolve.engine.IParamResolveEngine;
import com.json.resolve.engine.enums.ExpressTypeEnum;
import com.json.resolve.util.ExpressUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description
 * @Author fangjia
 * @Date 2020-06-04 12:40
 */
@Slf4j
public class ParamResolveEngineImpl implements IParamResolveEngine {


    /**
     * 表达式解析成可识别对象
     * @param express 表达式
     * @return
     */
    @Override
    public List<ParamResolveDto> paramResolvEngine(String express) {

        return ExpressUtil.resolveExpress(express);
    }


    /**
     * 根据表达式解析对应的表达式的具体值
     * @param jsonStr 待解析的json字符串
     * @param express 表达式
     * @return
     */
    @Override
    public String executeResolveExpress(String jsonStr, String express) {

        try {
            final List<ParamResolveDto> paramResolveDtos = paramResolvEngine(express);
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            Integer size = paramResolveDtos.size();

            Object result = null;
            for (int i = 0; i < paramResolveDtos.size(); i++) {
                ParamResolveDto paramResolveDto = paramResolveDtos.get(i);

                boolean lastLevel = (i + 1) == size ? true : false;
                if (ExpressTypeEnum.COMMON_TYPE.getCode().equals(paramResolveDto.getType())) {

                    result = getJsonObject(jsonObject, paramResolveDto, lastLevel);

                    if (lastLevel) {
                        return result.toString();
                    } else {
                        jsonObject = (JSONObject) result;
                    }
                } else if (ExpressTypeEnum.ARRAY_TYPE.getCode().equals(paramResolveDto.getType())) {

                    final JSONArray jsonArray = (JSONArray) jsonObject.get(paramResolveDto.getParamKey());

                    result = getJsonArrayObject(jsonArray, paramResolveDto, lastLevel);
                    if (lastLevel) {
                        return result.toString();
                    } else {
                        jsonObject = (JSONObject) result;
                    }
                }

            }

        } catch (final Throwable throwable) {
//            log.info("解析json出错，系统错误", throwable);
        }

        return null;
    }




    private Object getJsonObject(JSONObject inputJsonObject, ParamResolveDto paramResolveDto, boolean lastLevel) {

        String paramKey = paramResolveDto.getParamKey();

        if (lastLevel) {
            return inputJsonObject.get(paramKey);
        } else {
            return inputJsonObject.getJSONObject(paramKey);
        }
    }


    private Object getJsonArrayObject(JSONArray jsonArray, ParamResolveDto paramResolveDto, boolean lastLevel) {
        Integer index = paramResolveDto.getIndex();

        if (lastLevel) {
            return jsonArray.get(index);
        } else {
            return jsonArray.getJSONObject(index);
        }

    }


    /**
     * 替换json
     * @param jsonStr 待解析的json字符串
     * @param express 表达式
     * @param targetValue
     * @return
     */
    @Override
    public String executeReplacePramExpress(String jsonStr, String express, String targetValue) {

//        log.info("Before executeReplacePramExpress 's jsonStr is {}, and express is {}, and targetValue is {} ",
//                jsonStr, express, targetValue);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        try {
            final List<ParamResolveDto> paramResolveDtos = paramResolvEngine(express);


            Integer size = paramResolveDtos.size();

            Object result = null;
            JSONObject jsonObjectCopy = jsonObject;
            for (int i = 0; i < paramResolveDtos.size(); i++) {
                ParamResolveDto paramResolveDto = paramResolveDtos.get(i);

                boolean lastLevel = (i + 1) == size ? true : false;
                if (ExpressTypeEnum.COMMON_TYPE.getCode().equals(paramResolveDto.getType())) {
                    result = getJsonObject(jsonObjectCopy, paramResolveDto, lastLevel);

                    if (lastLevel) {
                        jsonObjectCopy.put(paramResolveDto.getParamKey(), targetValue);
                    } else {
                        jsonObjectCopy = (JSONObject) result;
                    }
                } else if (ExpressTypeEnum.ARRAY_TYPE.getCode().equals(paramResolveDto.getType())) {

                    final JSONArray jsonArray = (JSONArray) jsonObjectCopy.get(paramResolveDto.getParamKey());

                    result = getJsonArrayObject(jsonArray, paramResolveDto, lastLevel);
                    if (lastLevel) {
                        jsonArray.set(paramResolveDto.getIndex(), targetValue);
                    } else {
                        jsonObjectCopy = (JSONObject) result;
                    }
                }

            }

//            log.info("After executeReplacePramExpress 's jsonStr is {}" ,  JSON.toJSONString(jsonObject));

        } catch (final Throwable throwable) {
//            log.error("参数替换出错{}", throwable);
            throw new RuntimeException("参数替换出错");
        }
        return JSON.toJSONString(jsonObject);
    }



}
