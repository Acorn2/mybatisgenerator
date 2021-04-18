package com.msdn.generator.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @author hresh
 * @date 2021/4/17 18:14
 * @description
 */
public class StringUtils extends StrUtil {

    public StringUtils() {
    }

    /**
     * 驼峰转下划线
     *
     * @param name
     * @return
     */
    public static String camelToUnderscore(String name) {
        if (StrUtil.isEmpty(name)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String underscoreToCamel(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '_') {
                i++;
                c = str.charAt(i);
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 首字母转大写
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    //首字母转小写
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (StrUtil.isEmpty(str)) {
            return str;
        }

        char baseChar = str.charAt(0);
        char updatedChar;
        if (capitalize) {
            updatedChar = Character.toUpperCase(baseChar);
        } else {
            updatedChar = Character.toLowerCase(baseChar);
        }
        if (baseChar == updatedChar) {
            return str;
        }

        char[] chars = str.toCharArray();
        chars[0] = updatedChar;
        return new String(chars, 0, chars.length);
    }
}
