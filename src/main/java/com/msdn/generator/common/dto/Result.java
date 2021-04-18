package com.msdn.generator.common.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author hresh
 * @date 2021/4/16 9:27
 * @description
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private T data;
    private boolean success;
    private String code;
    private String message;
    private Long total;
    private Integer pageCount;
    private Integer pageSize;
    private Integer pageNum;

    public static <T> Result<T> ok() {
        Result<T> result = new Result<T>();
        result.success = true;
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>();
        result.data = data;
        result.success = true;
        return result;
    }

    public static <T> Result<List<T>> ok(List<T> list) {
        Result<List<T>> result = new Result();
        result.success = true;
        result.data = list;
        result.total = (long) list.size();
        return result;
    }

    public static <T> Result<T> ok(T data, String message) {
        Result<T> result = new Result<T>();
        result.data = data;
        result.success = true;
        result.message = message;
        return result;
    }

    public static <T> Result<T> error(String code, String message) {
        Result<T> result = new Result<T>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
