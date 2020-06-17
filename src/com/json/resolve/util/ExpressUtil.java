package com.json.resolve.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.json.resolve.dto.ParamResolveDto;
import com.json.resolve.engine.enums.ExpressTypeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @Description
 * @Author fangjia
 * @Date 2020-06-15 10:05
 */
public class ExpressUtil {


    public static List<ParamResolveDto> resolveExpress(String express) {

        if (StringUtils.isEmpty(express)) {
            return Lists.newArrayList();
        }

        if (!express.startsWith(CommonContants.PARAMS_EXPRESS_BEGIN)) {
//            log.info("表达式必须以$符号开头");
            return Lists.newArrayList();
        }

        if (!express.contains(CommonContants.PARAMS_DOT)) {
            return buildExpressNotContainsDot(express);
        }

        return buildExpresContainsDot(express);
    }

    public static List<ParamResolveDto> buildExpresContainsDot(String express) {

        final List<ParamResolveDto> paramResolveDtos = Lists.newArrayList();

        final String realExpress = express.substring(1);

        String[] expressElements = StringUtils.split(realExpress, CommonContants.PARAMS_DOT);

        for (int i = 0; i < expressElements.length; i++) {

            ParamResolveDto singleDto = buildExpressNotContainsDot(expressElements[i]).get(0);
            singleDto.setLevel(i + 1);
            paramResolveDtos.add(singleDto);
        }

        return paramResolveDtos;
    }


    /**
     * 表达式不包含DOT符号，说明只有一级表达式，可能是数组或者非数组
     *
     * @param express
     * @return
     */
    public static List<ParamResolveDto> buildExpressNotContainsDot(String express) {

        final List<ParamResolveDto> paramResolveDtos = Lists.newArrayList();
        String realExpress = express;
        if (express.startsWith(CommonContants.PARAMS_EXPRESS_BEGIN)) {
            realExpress = express.substring(1);
        }

        final ParamResolveDto paramResolveDto = new ParamResolveDto();
        if (realExpress.contains(CommonContants.PARAMS_ARRAY_LEFT) && realExpress.contains(CommonContants.PARAMS_ARRAY_RIGHT)) {
            //数组类型
            String key = realExpress.substring(0, realExpress.lastIndexOf(CommonContants.PARAMS_ARRAY_LEFT));
            paramResolveDto.setLevel(1);
            paramResolveDto.setParamKey(key);
            paramResolveDto.setType(ExpressTypeEnum.ARRAY_TYPE.getCode());
            paramResolveDto.setIndex(Integer.valueOf(realExpress.substring(realExpress.indexOf(CommonContants.PARAMS_ARRAY_LEFT) + 1, realExpress.indexOf(CommonContants.PARAMS_ARRAY_RIGHT))));
        } else {
            paramResolveDto.setLevel(1);
            paramResolveDto.setParamKey(realExpress);
            paramResolveDto.setType(ExpressTypeEnum.COMMON_TYPE.getCode());
        }
        paramResolveDtos.add(paramResolveDto);

        return paramResolveDtos;

    }


    public static void main(String[] args) {
        //"$itemPicTextInfo.picInfoList[1].picKey"
        System.out.println(JSON.toJSONString(buildExpressNotContainsDot("$itemPicTextInfo")));
        System.out.println(JSON.toJSONString(buildExpresContainsDot("$ddddd[10].fffff[1].aaaaa")));
    }
}
