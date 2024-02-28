package com.github.face.config;

import com.github.face.enums.ResponseEnum;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author wo
 */
@SuppressWarnings("all")
@Slf4j
@RestControllerAdvice(basePackages = "com.github.face") //设置处理返回类型的父路径
public class GlobalResponseBodyAdvice<T> implements ResponseBodyAdvice<T> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (object instanceof ResultT) {
            return object;
        }
        return ResultT.ok(object);
    }

    @ExceptionHandler({ServiceException.class})
    public ResultT handleBusiness(ServiceException e) {
        log.error("exception is {}", e.getMessage(), e);
        return ResultT.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class, BindException.class})
    public ResultT handleBusiness(IllegalArgumentException e) {
        return ResultT.fail(ResponseEnum.PARAM_INVALID.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResultT exceptions(Exception e) {
        log.error("exception is {}", e.getMessage(), e);
        return ResultT.fail(ResponseEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler({JwtException.class})
    public ResultT exceptions(JwtException e) {
        return ResultT.fail(ResponseEnum.TOKEN_INVALID);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResultT handleResourceNotFoundException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(errors)) {
            return ResultT.fail(99999, errors.get(0).getDefaultMessage());
        }
        return ResultT.fail(99999, "系统繁忙");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResultT validationErrorHandler(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        if (!CollectionUtils.isEmpty(errors)) {
            return ResultT.fail(99999, errors.get(0));
        }
        return ResultT.fail(99999, "系统繁忙");
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    public Object validationBindingErrorHandler(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            if (!ls.isEmpty()) {
                return ResultT.fail(99999, ls.get(0).getDefaultMessage());
            }
        }
        return ResultT.fail(99999, "系统繁忙");
    }

}
