package com.msdn.generator.common.dto;

/**
 * @author hresh
 * @date 2021/4/17 18:11
 * @description
 */
public class SimplePageInfo {

    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private Boolean count = true;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getCount() {
        return count != null && count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }
}
