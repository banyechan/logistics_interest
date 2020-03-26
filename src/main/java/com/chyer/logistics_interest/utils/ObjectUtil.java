package com.chyer.logistics_interest.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *  Object工具类
 */
@Slf4j
public class ObjectUtil {

    /**
     *获取某个对象所有字段拼接的 url
     * eg: &name=zhangsan&age=12&sex=man
     */
    public static String getParameterUrl(Object object){
        StringBuilder sb = new StringBuilder();
        Field[] fields=object.getClass().getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field tem : fields){
                String fieldName = tem.getName();
                if(!"serialVersionUID".equals(fieldName)){
                    String fieldValue = getValueByFieldName(fieldName,object);
                    if(StringUtils.isNotBlank(fieldValue) && !"ak".equals(fieldName)){
                        sb.append("&").append(fieldName).append("=").append(fieldValue);
                    }
                }
            }
        }
        log.info("---getParameterUrl  sb=" + sb.toString());
        return sb.toString();
    }

    //通过字段的名字,获取该字段的值
    private static String getValueByFieldName(String fieldName, Object object) {
        String result = null;
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = object.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(object, new Object[] {});
            if(value != null){
                result = value.toString();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
        return result;
    }



}
