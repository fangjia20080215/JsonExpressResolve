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

        TestMain testMain = new TestMain();
        testMain.testExecuteResolveExpress();

        testMain.testExecuteReplacePramExpress();
    }


    /**
     *
     */
    private void testExecuteResolveExpress() {
        final IParamResolveEngine paramResolveEngine = new ParamResolveEngineImpl();
        String json = "{\"itemId\":\"1152083490\",\"frontObject\":{\"frontCategoryList\":[{\"frontCategoryId\":1631,\"relationSort\":3}],\"frontField\":\"value\"}}";
        //表达式必须以$符号开头，其中表达式必须指定到最后一级
        String express = "$frontObject.frontCategoryList[0].relationSort";
        System.out.println(paramResolveEngine.executeResolveExpress(json, express));

    }


    /**
     *
     */
    private void testExecuteReplacePramExpress() {
        final IParamResolveEngine paramResolveEngine = new ParamResolveEngineImpl();
        String json = "{\"itemId\":\"1152083490\",\"frontObject\":{\"frontCategoryList\":[{\"frontCategoryId\":1631,\"relationSort\":3}],\"frontField\":\"value\"}}";
        //表达式必须以$符号开头，其中表达式必须指定到最后一级;
        String express = "$frontObject.frontCategoryList[0].relationSort";
        //将targetValue=6666666   的值替换json对象中的表达式对应的变量具体值，并返回替换之后的json字符串
        System.out.println(paramResolveEngine.executeReplacePramExpress(json, express, "6666666"));
    }

}
