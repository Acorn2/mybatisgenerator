package com.msdn.generator.common.config;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hresh
 * @date 2021/4/18 17:38
 * @description Mybatis plus 配置类
 */
@ConditionalOnBean({com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration.class})
public class MybatisPlusAutoConfiguration {
    public MybatisPlusAutoConfiguration() {
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MybatisPlusAutoConfiguration.FillFieldConfiguration();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        List<ISqlParser> sqlParserList = new ArrayList();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    public static class FillFieldConfiguration implements MetaObjectHandler {
        public FillFieldConfiguration() {
        }

        @Override
        public void insertFill(MetaObject metaObject) {
            DateTime now = DateUtil.date();
            metaObject.setValue("createBy", "1");
            metaObject.setValue("createName", "admin");
            metaObject.setValue("createTime", now);
            metaObject.setValue("updateBy", "1");
            metaObject.setValue("updateName", "admin");
            metaObject.setValue("updateTime", now);
            this.strictInsertFill(metaObject, "orgId", String.class, "xxx");
            this.strictInsertFill(metaObject, "platformId", String.class, "xxx");
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            DateTime now = DateUtil.date();
            metaObject.setValue("updateBy", "1");
            metaObject.setValue("updateName", "admin");
            metaObject.setValue("updateTime", now);
        }
    }
}
