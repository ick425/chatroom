package com.github.face.base;

import com.github.face.user.entity.SysUser;

/**
 * 基础controller
 *
 * @author wo
 */
public class BaseController {

    private SysUser getSysUser() {
        return new SysUser();
    }
}
