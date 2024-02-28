package com.github.face.config;

import com.github.face.enums.ResponseEnum;
import lombok.Getter;

/**
 * 基础异常类
 *
 * @author wo
 */
@Getter
public class ServiceException extends RuntimeException {

    private final int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
    }

}
