package com.github.face.login;

import cn.hutool.crypto.SecureUtil;
import com.github.face.config.ServiceException;
import com.github.face.enums.ResponseEnum;
import com.github.face.user.entity.SysUser;
import com.github.face.user.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 后台登录
 *
 * @author wangcl
 * @since 2024-02-26
 */
@Valid
@RestController
@Tag(name = "登录", description = "/api/admin/login")
@RequestMapping("/api/admin/login")
public class LoginController {
    ThreadLocal<Object> local = new ThreadLocal<>();
    @Resource
    private SysUserService sysUserService;

    @Operation(summary = "登录", parameters = {
//            @Parameter(name = "userName", description = "账户")
    })
    @PostMapping()
    public SysUser login(String userName, String pwd) {
        SysUser user = sysUserService.getUserByUserName(userName);
        if (user == null) {
            throw new ServiceException(ResponseEnum.PASSWORD_ERROR);
        }
        String md5 = SecureUtil.md5(pwd + user.getSalt());
        if (!md5.equals(user.getPwd())) {
            throw new ServiceException(ResponseEnum.PASSWORD_ERROR);
        }
        return user;
    }

}