package com.msdn.generator.utils;


import cn.hutool.core.convert.Convert;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hresh
 * @date 2021/4/18 10:29
 * @description
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.syso();
    }

    public void syso(){
        URL resource = this.getClass().getResource("/templates/mybatis");
        System.out.println(resource.getFile());
//        System.out.println(new File(resource.getFile()));
//        String currentPath = resource.getPath().substring(1);
//        int index = currentPath.indexOf("com");
//        System.out.println(currentPath.substring(0,index));

        Map<String,String> map = new HashMap<>();
        map.put("name","hresh");
        System.out.println(Convert.toStrArray(map));
    }
}
