package com.github.face.interceptor;

import com.alibaba.fastjson2.JSON;
import com.github.face.config.ResultT;
import com.github.face.login.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.github.face.enums.ResponseEnum.TOKEN_INVALID;

/**
 * 拦截器
 *
 * @author wo
 */
@Component
@Slf4j
public class JwtValidateInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // X-Token 是前端定义的token标头，与前端保持一致
        String token = request.getHeader("token");
        if (token != null) {
            try {
                Claims claims = jwtUtil.parseToken(token);
                log.info(request.getRequestURI() + "验证通过：");
                return true;
            } catch (Exception e) {
                log.error(request.getRequestURI() + "验证失败，禁止访问，错误日志==> ", e);
            }
        }
        log.error("验证失败, 禁止访问, 请求方式: {}, URI: {}, 参数: {}", request.getMethod(), request.getRequestURI(), request.getQueryString());
        // 创建一个返回对象，当token错误后反馈给前端
        ResultT fail = ResultT.fail(TOKEN_INVALID);
        // 验证不成功，给前端返回数据
        response.setContentType("application/json;charset=utf-8");  // 定义返回数据格式
        response.getWriter().write(JSON.toJSONString(fail));    // 将对象序列化后以json格式反馈至前端
        return false;   // 拦截当前用户的操作
    }
}