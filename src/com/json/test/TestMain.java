package com.json.test;


import com.json.resolve.engine.IParamResolveEngine;
import com.json.resolve.engine.impl.ParamResolveEngineImpl;


/**
 * JSON表达式解析工具
 *
 * <ol>
 *     <li>根据json表达式解析json数据并获取表达式对应的值</li>
 *
 *     <li>根据JSON表达式解析json数据并替换json数据内部的具体值，并返回新的json数据</li>
 * </ol>
 */
public class TestMain {


    public static void main(String[] args) {
        final IParamResolveEngine paramResolveEngine = new ParamResolveEngineImpl();
    }
}
