package com.msdn.generator.common.dto;

/**
 * 封装API的错误码
 */
public interface IErrorCode {
    /**
     * 错误码
     */
    String getResultCode();

    /**
     * 错误描述
     */
    String getResultMsg();
}
