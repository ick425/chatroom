package com.github.face.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.face.user.entity.SysUser;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author wangcl
 * @since 2024-02-26
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser getUserByUserName(String username);

    /**
     * 导出测试
     */
    void exportTest(HttpServletResponse response);
}
