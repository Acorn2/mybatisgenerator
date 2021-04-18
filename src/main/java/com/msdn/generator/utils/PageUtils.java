package com.msdn.generator.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.github.pagehelper.Page;
import com.msdn.generator.common.dto.SimplePageInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hresh
 * @date 2021/4/17 18:07
 * @description 分页工具类
 */
@Slf4j
public class PageUtils extends PageUtil {

    public static <S, D> Page<D> convert(Page<S> sourceList, Class<D> targetClass) {
        try {
            Page<D> result = new Page<>(sourceList.getPageNum(), sourceList.getPageSize(), sourceList.isCount());
            result.setTotal(sourceList.getTotal());
            for (S s : sourceList) {
                D temp = BeanUtils.copyProperties(s, targetClass);
                result.add(temp);
            }
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    // 分页查询
    public static <T> IPage<T> getPage(SimplePageInfo pageInfo) {

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(Convert.toLong(pageInfo.getPageNum(), 1L),
                Convert.toLong(pageInfo.getPageSize(), 10L));

        return page;
    }
}
