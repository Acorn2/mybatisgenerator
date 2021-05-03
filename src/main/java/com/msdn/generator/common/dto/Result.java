package com.msdn.generator.common.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author hresh
 * @date 2021/4/16 9:27
 * @description 通用返回对象
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private T data;
    private String code;
    private String message;
    private Long total;

    protected Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return ok((T) null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return new Result<T>(CommonEnum.SUCCESS.getResultCode(), CommonEnum.SUCCESS.getResultMsg(), data);
    }

    /**
     * 成功返回list结果
     * @param list 获取的数据
     * @return
     */
    public static <T> Result<List<T>> ok(List<T> list) {
        Result<List<T>> listResult = new Result<>(CommonEnum.SUCCESS.getResultCode(), CommonEnum.SUCCESS.getResultMsg(), list);
        listResult.total = (long) list.size();
        return listResult;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> Result<T> ok(T data, String message) {
        return new Result<T>(CommonEnum.SUCCESS.getResultCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> Result<T> failed(IErrorCode errorCode) {
        return new Result<T>(errorCode.getResultCode(), errorCode.getResultMsg(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> Result<T> failed(IErrorCode errorCode, String message) {
        return new Result<T>(errorCode.getResultCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Result<T> failed(String message) {
        return new Result<T>(CommonEnum.INTERNAL_SERVER_ERROR.getResultCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> failed() {
        return failed(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Result<T> validateFailed() {
        return failed(CommonEnum.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> validateFailed(String message) {
        return new Result<T>(CommonEnum.VALIDATE_FAILED.getResultCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<T>(CommonEnum.UNAUTHORIZED.getResultCode(), CommonEnum.UNAUTHORIZED.getResultMsg(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(CommonEnum.FORBIDDEN.getResultCode(), CommonEnum.FORBIDDEN.getResultMsg(), data);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
