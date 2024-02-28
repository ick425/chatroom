package com.github.face.config;

import com.github.face.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.github.face.enums.ResponseEnum.SUCCESS;
import static com.github.face.enums.ResponseEnum.SYSTEM_ERROR;

/**
 * 全局返回对象
 *
 * @author wo
 */
@Getter
@AllArgsConstructor
public class ResultT {

    private Integer code;
    private String msg;
    private Object data;

    public static ResultT ok() {
        return new ResultT(SUCCESS.getCode(), SUCCESS.getMsg(), null);
    }

    public static ResultT ok(Object data) {
        return new ResultT(SUCCESS.getCode(), SUCCESS.getMsg(), data);
    }

    public static ResultT fail() {
        return new ResultT(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getMsg(), null);
    }

    public static ResultT fail(String msg) {
        return new ResultT(SYSTEM_ERROR.getCode(), msg, null);
    }

    public static ResultT fail(ResponseEnum responseEnum) {
        return new ResultT(responseEnum.getCode(), responseEnum.getMsg(), null);
    }

    public static ResultT fail(Integer code, String msg) {
        return new ResultT(code, msg, null);
    }

    public static ResultT fail(ResponseEnum responseEnum, String msg) {
        return new ResultT(responseEnum.getCode(), msg, null);
    }
}
