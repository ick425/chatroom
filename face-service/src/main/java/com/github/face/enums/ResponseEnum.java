package com.github.face.enums;

import lombok.Getter;

/**
 * 全局异常信息枚举
 * 自定义异常使用1000~9999
 *
 * @author wo
 */
@Getter
public enum ResponseEnum {

    /**
     * 全局异常
     */
    SUCCESS(200, "成功"),
    SYSTEM_ERROR(500, "系统异常，请联系管理员"),
    LOGIN_ERROR(1001, "登陆失败"),
    TOKEN_INVALID(1002, "token无效，请重新登录"),

    ;

    private final int code;
    private final String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
