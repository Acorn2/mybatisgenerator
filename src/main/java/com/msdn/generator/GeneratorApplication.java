package com.msdn.generator;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hresh
 * @date 2021/4/16 23:58
 * @description
 */
@EnableSwagger2Doc
@SpringBootApplication
public class GeneratorApplication {

    /**
     * 测试的时候添加参数 -h 47.103.92.197 -P 3306 -d db_fl_foundation -u zhd -p ZhdJk2020@pwd -m foundation -g base -t t_jc_warehouse,t_jc_prod_area
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }
}
