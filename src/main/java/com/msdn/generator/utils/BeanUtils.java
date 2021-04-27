package com.msdn.generator.utils;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author hresh
 * @date 2021/4/17 17:18
 * @description Bean相关工具类，在Spring项目中使用，则继承org.springframework.beans.BeanUtils
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static void copyPropertiesIgnoreNull(Object source, Object target) throws BeansException {
        copyProperties(source, target, getNullPropertyNames(source));
    }

    public static <D> D copyProperties(Object source, Class<D> clazz) throws RuntimeException {
        Object object;
        try {
            object = clazz.newInstance();
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }

        copyProperties(source, object);
        return (D) object;
    }

    public static <S, D> List<D> copyProperties(List<S> source, Class<D> clazz) {
        try {
            List<D> destList = new ArrayList();
            Iterator value = source.iterator();

            while (value.hasNext()) {
                S s = (S) value.next();
                D d = clazz.newInstance();
                copyProperties(s, d);
                destList.add(d);
            }
            return destList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set emptyNames = new HashSet();
        PropertyDescriptor[] newPds = pds;

        for (int i = 0; i < pds.length; ++i) {
            PropertyDescriptor pd = newPds[i];
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return (String[]) emptyNames.toArray(result);
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        return BeanUtil.mapToBean(map, clazz, false, null);
    }

    /**
     * null值设置默认值
     *
     * @param obj
     */
    public static void setFeidValueNotNull(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    if (field.getGenericType().toString().equals("class java.lang.String")) {
                        field.set(obj, "");
                    } else if (field.getGenericType().toString().equals("class java.lang.Integer")) {
                        field.set(obj, 0);
                    } else if (field.getGenericType().toString().equals("class java.lang.Double")) {
                        field.set(obj, 0.0);
                    } else if (field.getGenericType().toString().equals("class java.lang.Long")) {
                        field.set(obj, 0L);
                    } else if (field.getGenericType().toString().equals("class java.math.BigDecimal")) {
                        field.set(obj, BigDecimal.ZERO);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
