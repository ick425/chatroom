package com.github.face.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局返回对象
 *
 * @author wo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object result = null;

    public Result success() {
        return new Result(200, "success", null);
    }

    public Result success(int code, String msg) {
        return new Result(code, msg, null);
    }

    public Result success(int code, String msg, Object result) {
        return new Result(code, msg, result);
    }

    public Result error(String msg) {
        return new Result(500, msg, null);
    }

    public Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }

}
